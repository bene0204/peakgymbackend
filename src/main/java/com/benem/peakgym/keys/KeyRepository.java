package com.benem.peakgym.keys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<KeyEntity, String> {
  @Query(" SELECT k.userId.userId FROM KeyEntity k" +
           " WHERE k.key = :key")
  String getUserIdGotKey(String key);

  List<KeyEntity> findAllByUserIdNotNull();
}
