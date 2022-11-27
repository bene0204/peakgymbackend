package com.benem.peakgym.keys;

import com.benem.peakgym.keys.dto.SetupKeysDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyController {

  private final KeyService keyService;

  @PostMapping("admin/api/keys/setup")
  public void setupKeys(@RequestBody SetupKeysDTO setup) {
    keyService.setupKeys(setup);
  }

  @PatchMapping("management/api/keys/checkin/{key}")
  public void checkInUser(@PathVariable("key") String key, @RequestParam("membership") String membership, @RequestParam("userId") String userId) {
    keyService.checkInUser(key, membership, userId);
  }

  @PatchMapping("management/api/keys/checkout/{key}")
  public void checkOutUser(@PathVariable("key") String key) {
    keyService.chechOutUser(key);
  }
}
