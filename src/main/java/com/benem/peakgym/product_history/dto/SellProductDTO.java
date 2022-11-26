package com.benem.peakgym.product_history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellProductDTO {

  private String typeId;

  private Integer quantity;

  private String paymentMethod;
}
