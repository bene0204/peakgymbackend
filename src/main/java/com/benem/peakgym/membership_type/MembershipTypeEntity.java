package com.benem.peakgym.membership_type;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.membership_history.MembershipHistoryEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "membership_type")
public class MembershipTypeEntity {

    @Id
    private final String membershipTypeId = UUID.randomUUID().toString();

    @Length(min = 5, max = 50)
    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer numberOfDays;

    private Integer numberOfOccasion;

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<MembershipHistoryEntity> memberships;
}
