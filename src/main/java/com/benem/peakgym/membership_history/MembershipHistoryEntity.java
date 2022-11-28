package com.benem.peakgym.membership_history;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.key_history.KeyHistoryEntity;
import com.benem.peakgym.membership_type.MembershipTypeEntity;
import com.benem.peakgym.user.UserEntity;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private final LocalDateTime sellingDate = LocalDateTime.now();

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private Integer occasionsLeft;

    @NotNull
    private Integer price;

    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity owner;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "membershipTypeId")
    private MembershipTypeEntity type;

    @JsonIgnore
    @OneToMany(mappedBy = "membership")
    private List<KeyHistoryEntity> keyHistory;
}
