package com.benem.peakgym.product_history.projections;

import java.time.LocalDateTime;

public interface TransactionProjection {

  String getName();

  Integer getPrice();

  Integer getQuantity();

  LocalDateTime getSellingDate();

  String getBuyer();
}
