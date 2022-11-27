package com.benem.peakgym.key_history;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyHistoryController {

  private final KeyHistoryService keyHistoryService;

  @GetMapping("management/api/keyhistory/{userId}")
  public List<KeyHistoryEntity> getKeyHistoryForUser(@PathVariable("userId") String userId) {
    return keyHistoryService.getKeyHistoryForUser(userId);
  }
}
