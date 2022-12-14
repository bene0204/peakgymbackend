package com.benem.peakgym.user;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.membership_history.MembershipHistoryEntity;
import com.benem.peakgym.product_history.ProductHistoryEntity;
import com.benem.peakgym.auth.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    private final String userId = UUID.randomUUID().toString();

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private LocalDate birthDate;

    @Column(unique = true)
    @NotBlank
    @Email
    private String email;

    @JsonIgnore
    private String password = "alma023";

    private String phoneNumber;

    private int balance = 0;

    @Enumerated(EnumType.STRING)
    private USER_ROLE role = USER_ROLE.BASIC;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<MembershipHistoryEntity> memberships;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer")
    private List<ProductHistoryEntity> products;
}
