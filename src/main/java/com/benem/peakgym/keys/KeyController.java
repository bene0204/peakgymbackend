package com.benem.peakgym.keys;

import java.util.List;

import com.benem.peakgym.key_history.dto.KeyResponseDTO;
import com.benem.peakgym.keys.dto.CheckInOrOutDTO;
import com.benem.peakgym.keys.dto.SetupKeysDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KeyController {

  private final KeyService keyService;

  @PostMapping("admin/api/keys/setup")
  public void setupKeys(@RequestBody SetupKeysDTO setup) {
    keyService.setupKeys(setup);
  }

  @GetMapping("management/api/keys")
  public List<KeyEntity> getKeys(){
    return keyService.getKeys();
  }

  @PatchMapping("management/api/keys/checkin/{key}")
  public List<KeyEntity> checkInUser(@PathVariable("key") String key, @RequestBody CheckInOrOutDTO dto) {
    return keyService.checkInUser(key, dto);
  }

  @PatchMapping("management/api/keys/checkout/{key}")
  public void checkOutUser(@PathVariable("key") String key) {
    keyService.chechOutUser(key);
  }

  @GetMapping("management/api/keys/{key}")
  public KeyResponseDTO getUserIdGotKey(@PathVariable("key") String key){
    return keyService.getUserIdGotKey(key);
  }
}
