package com.benem.peakgym.key_history.projection;

import java.time.LocalDateTime;

public interface KeyHistoryProjection {

  String getCheckInId();

  LocalDateTime getCheckInTime();

  LocalDateTime getCheckOutTime();

  String getKey();

  String getName();
}
