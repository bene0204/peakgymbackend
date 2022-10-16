package com.benem.peakgym.product_history.projections;

import java.time.LocalDateTime;

import com.benem.peakgym.util.enums.PAYMENT_METHOD;

public interface TransactionProjection {

  String getName();

  Integer getPrice();

  Integer getQuantity();

  PAYMENT_METHOD getPaymentMethod();
  LocalDateTime getSellingDate();

  String getBuyer();
}
