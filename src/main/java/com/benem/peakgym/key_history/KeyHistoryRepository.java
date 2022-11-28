package com.benem.peakgym.key_history;

import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.key_history.projection.KeyHistoryProjection;
import com.benem.peakgym.keys.KeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyHistoryRepository extends JpaRepository<KeyHistoryEntity, String> {

  KeyHistoryEntity findFirstByKeyAndCheckOutTimeIsNull(KeyEntity key);
  @Query(" SELECT kh.checkInId AS checkInId," +
           "kh.checkInTime AS checkInTime," +
           "kh.checkOutTime AS checkOutTime," +
           "k.key AS key," +
           "concat(u.lastName,' ', u.firstName) AS name FROM KeyHistoryEntity kh" +
           " INNER JOIN KeyEntity k ON kh.key.key = k.key" +
           " INNER JOIN UserEntity u ON kh.user.userId = u.userId" +
           " WHERE u.userId = :userId" +
           " AND kh.membership.membershipId = :membershipId")
  List<KeyHistoryProjection> getKeyHistoryForUser(String userId, String membershipId);

  @Query(" SELECT kh.checkInId AS checkInId," +
           "kh.checkInTime AS checkInTime," +
           "kh.checkOutTime AS checkOutTime," +
           "k.key AS key," +
           "concat(u.lastName, ' ',u.firstName) AS name FROM KeyHistoryEntity kh" +
           " INNER JOIN KeyEntity k ON kh.key.key = k.key" +
           " INNER JOIN UserEntity u ON kh.user.userId = u.userId" +
           " WHERE key = :key" +
           " AND checkInTime BETWEEN :fromDate AND :toDate")
  List<KeyHistoryProjection> getKeyHistoryForKey(String key, LocalDateTime fromDate, LocalDateTime toDate);
}
