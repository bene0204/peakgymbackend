package com.benem.peakgym.key_history;

import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.key_history.projection.KeyHistoryProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyHistoryController {

  private final KeyHistoryService keyHistoryService;

  @GetMapping("management/api/keyhistoryforuser/{userId}")
  public List<KeyHistoryProjection> getKeyHistoryForUser(@PathVariable("userId") String userId,
                                                     @RequestParam("membershipId") String membershipId) {
    return keyHistoryService.getKeyHistoryForUser(userId, membershipId);
  }

  @GetMapping("management/api/keyhistory/{key}")
  public List<KeyHistoryProjection> getKeyHistoryForKey(@PathVariable("key") String key,
                                                        @RequestParam("fromDate") LocalDateTime fromDate,
                                                        @RequestParam("toDate") LocalDateTime toDate) {
    return keyHistoryService.getKeyHistoryForKey(key, fromDate, toDate);
  }
}
