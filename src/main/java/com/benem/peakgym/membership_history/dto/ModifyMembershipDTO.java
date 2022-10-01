package com.benem.peakgym.membership_history.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyMembershipDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer occasionsLeft;
}
