package com.benem.peakgym.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.benem.peakgym.PeakgymApplication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static javax.mail.Message.RecipientType.*;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private static final String APPLICATION_NAME = "PEAKGYM MAILER";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";
  private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
  private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
  private static final String PEAKGYM_MAIL = "peakgymdummyemail@gmail.com";

  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
    throws IOException {

    InputStream in = PeakgymApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
    if (in == null) {
      throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
    }
    GoogleClientSecrets clientSecrets =
      GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));


    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
      HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                                         .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                                         .setAccessType("offline")
                                         .build();
    LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

    return credential;
  }

  private void sendMail(String toEmailAddress, String subject, String body) throws GeneralSecurityException, IOException, MessagingException {
    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                      .setApplicationName(APPLICATION_NAME)
                      .build();


    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);
    MimeMessage email = new MimeMessage(session);
    email.setFrom(new InternetAddress(PEAKGYM_MAIL));
    email.addRecipient(TO,
      new InternetAddress(toEmailAddress));
    email.setSubject(subject);
    email.setText(body);

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    email.writeTo(buffer);
    byte[] rawMessageBytes = buffer.toByteArray();
    String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
    Message message = new Message();
    message.setRaw(encodedEmail);

    try {
      message = service.users().messages().send("me", message).execute();
      System.out.println("message id: " + message.getId());
      System.out.println(message.toPrettyString());
    } catch (GoogleJsonResponseException e) {
      GoogleJsonError error = e.getDetails();
      if (error.getCode() == 403) {
        System.err.println("Unable to send message: " + e.getDetails());
      } else {
        throw e;
      }
    }
  }

  @Async
  public void sendWelcomeEmail(String name, String toAddress, String password) throws MessagingException, GeneralSecurityException, IOException {
    String subject = "Üdvözöljük vendégeink között";
    String body = """
      Tisztelt %s,
      
      Szeretnénk megköszönni, hogy edzőtermünket választotta.
      
      Az oldalra a megadott emaillel és jelszóval tud belépni:
      
      email: %s
      jelszó: %s
      
      A jelszó automatikusan generált, az első bejelentkezés után javasolt megváltoztatni.
      
      Üdvözlettel:
      
      Peakgym
      
      """.formatted(name, toAddress, password);

    sendMail(toAddress, subject, body);
  }
}
