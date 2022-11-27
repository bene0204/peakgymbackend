package com.benem.peakgym.keys;

import java.util.ArrayList;
import java.util.List;

import com.benem.peakgym.key_history.KeyHistoryService;
import com.benem.peakgym.keys.dto.SetupKeysDTO;
import com.benem.peakgym.membership_history.MembershipHistoryService;
import com.benem.peakgym.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class KeyService {

  private final KeyRepository keyRepository;

  private final KeyHistoryService keyHistoryService;

  private final MembershipHistoryService membershipHistoryService;

  private final UserService userService;

  public void setupKeys(SetupKeysDTO setup) {
    List<KeyEntity> keys = new ArrayList<>();

    for (int i = 0; i < setup.getMaleKeysNumber(); i++) {
      KeyEntity key = KeyEntity.builder()
                        .key("F" + (i+1))
                        .userId(null)
                        .build();
      keys.add(key);
    }
    for (int i = 0; i < setup.getFemaleKeysNumber(); i++) {
      KeyEntity key = KeyEntity.builder()
                        .key("N" + (i+1))
                        .userId(null)
                        .build();
      keys.add(key);
    }

    keyRepository.saveAll(keys);
  }

  @Transactional
  public void checkInUser(String keyNumber, String membershipId, String userId) {
    var key = keyRepository.findById(keyNumber).get();
    var membership = membershipHistoryService.findMembershipById(membershipId);
    var user = userService.findUserById(userId);
    if (membershipHistoryService.isActive(membership)) {
      key.setUserId(user);
      keyRepository.save(key);
      if (membership.getOccasionsLeft() != null) {
        membershipHistoryService.removeOneOccasion(membership);
      }
      keyHistoryService.checkInUser(user, key);
    }
  }

  @Transactional
  public void chechOutUser(String keyNumber) {
    var key = keyRepository.findById(keyNumber).get();

    key.setUserId(null);
    keyRepository.save(key);

    keyHistoryService.checkOutUser(key);
  }
}
