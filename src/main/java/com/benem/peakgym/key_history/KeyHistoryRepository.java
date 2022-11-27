package com.benem.peakgym.key_history;

import java.util.List;

import com.benem.peakgym.keys.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyHistoryRepository extends JpaRepository<KeyHistoryEntity, String> {

  KeyHistoryEntity findFirstByKeyAndCheckOutTimeIsNull(KeyEntity key);
  @Query(" SELECT kh FROM KeyHistoryEntity kh " +
           "WHERE kh.user.userId = :userId" +
           " ORDER BY kh.checkInTime DESC")
  List<KeyHistoryEntity> getKeyHistoryForUser(String userId);
}
