package com.benem.peakgym.product_history;

import java.time.LocalDateTime;
import java.util.List;

import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistoryEntity, String> {

    @Query("SELECT pt.name AS name, ph.price AS price, ph.quantity AS quantity, ph.paymentMethod AS paymentMethod, ph.sellingDate AS sellingDate, CONCAT(u.lastName,' ', u.firstName ) AS buyer " +
           "FROM ProductTypeEntity AS pt JOIN ProductHistoryEntity AS ph ON ph.product.productTypeId = pt.productTypeId " +
           "JOIN UserEntity as u ON ph.buyer.userId = u.userId" +
           " WHERE ph.sellingDate BETWEEN :fromDate AND :toDate")
    List<TransactionProjection> getProductHistoryBetween(LocalDateTime fromDate, LocalDateTime toDate);

    @Query("SELECT SUM(ph.price) FROM ProductHistoryEntity AS ph" +
           " WHERE ph.sellingDate BETWEEN :fromDate AND :toDate" +
           " AND ph.paymentMethod = :method")
    Integer getSumOfTransactionsByPaymentMethod(LocalDateTime fromDate, LocalDateTime toDate, PAYMENT_METHOD method);
}
