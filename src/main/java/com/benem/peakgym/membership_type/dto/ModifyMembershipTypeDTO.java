package com.benem.peakgym.membership_type.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModifyMembershipTypeDTO {

    private String name;

    private Integer price;

    private Integer numberOfDays;

    private Integer numberOfOccasion;
}
