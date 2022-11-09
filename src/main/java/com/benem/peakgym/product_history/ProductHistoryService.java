package com.benem.peakgym.product_history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.benem.peakgym.membership_history.MembershipHistoryService;
import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.product_type.ProductTypeService;
import com.benem.peakgym.user.UserService;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductHistoryService {

  private final ProductHistoryRepository productHistoryRepository;

  private final ProductTypeService productTypeService;

  private final MembershipHistoryService membershipHistoryService;

  private final UserService userService;
  @Transactional
  public ProductHistoryEntity sellProduct(String typeId, String buyerId, PAYMENT_METHOD paymentMethod, Integer quantity) {
    var buyer = userService.findUserById(buyerId);
    var type = productTypeService.findProductTypeById(typeId);

    var productHistory = ProductHistoryEntity.builder()
                           .product(type)
                           .buyer(buyer)
                           .price(type.getPrice() * quantity)
                           .paymentMethod(paymentMethod)
                           .quantity(quantity)
                           .build();

    productTypeService.removeQuantityFromProduct(typeId, quantity);

    return productHistoryRepository.save(productHistory);
  }

  public List<TransactionProjection> getTransactionsBetween(String fromDate, String toDate)  {
    var from = LocalDateTime.parse(fromDate + "T00:00:00");
    var to = LocalDateTime.parse(toDate + "T23:59:59");

    var membershipTransactions = membershipHistoryService.getMembershipTransactionsBetween(from, to);
    var productTransactions = productHistoryRepository.getProductHistoryBetween(from, to);

    List<TransactionProjection> transactions = new ArrayList<>(membershipTransactions);
    transactions.addAll(productTransactions);

    transactions.sort((o1, o2) -> {
      if (o1.getSellingDate().isBefore(o2.getSellingDate())) {
        return 1;
      } else {
        return -1;
      }
    });
    return transactions;
  }

  public Integer getSumOfTransactionsByPaymentMethod(String fromDate, String toDate, PAYMENT_METHOD paymentMethod) {
    var from = LocalDateTime.parse(fromDate + "T00:00:00");
    var to = LocalDateTime.parse(toDate + "T23:59:59");

    Integer sumOfProductTransactions = productHistoryRepository.getSumOfTransactionsByPaymentMethod(from, to, paymentMethod);
    Integer sumOfMembershipTransactions = membershipHistoryService.getSumOfTransactionsByPaymentMethod(from, to, paymentMethod);

    return sumOfMembershipTransactions + sumOfProductTransactions;
  }
}
