package com.benem.peakgym.keys;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.benem.peakgym.key_history.KeyHistoryEntity;
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
@Table(name = "keys")
public class KeyEntity {

  @Id
  private String key;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private UserEntity userId;

  @OneToMany(mappedBy = "key")
  @JsonIgnore
  List<KeyHistoryEntity> keyHistory;
}
