package com.benem.peakgym.product_history;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.benem.peakgym.product_type.ProductTypeEntity;
import com.benem.peakgym.user.UserEntity;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
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
@Table(name = "product_history")
public class ProductHistoryEntity {

    @Id
    private final String transactionId = UUID.randomUUID().toString();

    private final LocalDateTime sellingDate = LocalDateTime.now();

    @NotNull
    private Integer price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private PAYMENT_METHOD paymentMethod;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private UserEntity buyer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productTypeId")
    private ProductTypeEntity product;
}
