package com.benem.peakgym.key_history;

import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.key_history.projection.KeyHistoryProjection;
import com.benem.peakgym.keys.KeyEntity;
import com.benem.peakgym.membership_history.MembershipHistoryEntity;
import com.benem.peakgym.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeyHistoryService {

  private final KeyHistoryRepository keyHistoryRepository;

  public void checkInUser(UserEntity user, KeyEntity key, MembershipHistoryEntity membership) {
    var history = KeyHistoryEntity.builder()
                    .checkInTime(LocalDateTime.now())
                    .key(key)
                    .user(user)
                    .membership(membership)
                    .build();

    keyHistoryRepository.save(history);
  }

  public KeyHistoryEntity findLastRecordForKey(KeyEntity key) {
    return keyHistoryRepository.findFirstByKeyAndCheckOutTimeIsNull(key);
  }

  public void checkOutUser(KeyEntity key) {
    var keyHistoryRecord = findLastRecordForKey(key);

    keyHistoryRecord.setCheckOutTime(LocalDateTime.now());

    keyHistoryRepository.save(keyHistoryRecord);
  }

  public List<KeyHistoryProjection> getKeyHistoryForUser(String userId, String membershipId) {
    return keyHistoryRepository.getKeyHistoryForUser(userId, membershipId);
  }

  public List<KeyHistoryProjection> getKeyHistoryForKey(String key, LocalDateTime fromDate, LocalDateTime toDate) {
    return keyHistoryRepository.getKeyHistoryForKey(key, fromDate, toDate);
  }
}
