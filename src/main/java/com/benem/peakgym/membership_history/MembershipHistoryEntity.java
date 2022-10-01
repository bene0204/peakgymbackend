package com.benem.peakgym.membership_history;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.membership_type.MembershipTypeEntity;
import com.benem.peakgym.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "membership_history")
public class MembershipHistoryEntity {

    @Id
    private final String membershipId = UUID.randomUUID().toString();

    private final Date sellingDate = new Date();

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private Integer occasionsLeft;

    @NotNull
    private Integer price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity owner;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "membershipTypeId")
    private MembershipTypeEntity type;
}
