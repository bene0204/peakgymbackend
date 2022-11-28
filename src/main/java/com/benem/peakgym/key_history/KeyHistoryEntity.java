package com.benem.peakgym.key_history;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.benem.peakgym.keys.KeyEntity;
import com.benem.peakgym.membership_history.MembershipHistoryEntity;
import com.benem.peakgym.user.UserEntity;
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
@Table(name = "key_history")
public class KeyHistoryEntity {

  @Id
  private final String checkInId = UUID.randomUUID().toString();

  private LocalDateTime checkInTime;

  private LocalDateTime checkOutTime;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "membershipId", referencedColumnName = "membershipId")
  private MembershipHistoryEntity membership;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "keyId", referencedColumnName = "key")
  private KeyEntity key;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private UserEntity user;
}
