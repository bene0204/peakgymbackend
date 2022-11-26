package com.benem.peakgym.membership_history.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellMembershipDTO {

  private String typeId;

  @Nullable
  private LocalDate startDate;

  private String paymentMethod;
}
