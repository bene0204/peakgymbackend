package com.benem.peakgym.product_type;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.product_history.ProductHistoryEntity;
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
@Getter
@Setter
@Builder
@Table(name = "product_type")
public class ProductTypeEntity {

  @Id
  private final String productTypeId = UUID.randomUUID().toString();

  @NotBlank
  private String name;

  @NotNull
  private Integer price;

  private Integer quantity = 0;

  @JsonIgnore
  @OneToMany(mappedBy = "product")
  private List<ProductHistoryEntity> products;
}
