package com.benem.peakgym.product_history;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.benem.peakgym.membership_history.MembershipHistoryService;
import com.benem.peakgym.product_history.dto.SellProductDTO;
import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.product_type.ProductTypeService;
import com.benem.peakgym.user.UserService;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import lombok.RequiredArgsConstructor;
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
  public void sellProduct(String buyerId, List<SellProductDTO> productDTOS) {

    for(SellProductDTO dto : productDTOS) {
      var buyer = userService.findUserById(buyerId);
      var type = productTypeService.findProductTypeById(dto.getTypeId());

      var productHistory = ProductHistoryEntity.builder()
                             .product(type)
                             .buyer(buyer)
                             .price(type.getPrice() * dto.getQuantity())
                             .paymentMethod(PAYMENT_METHOD.valueOf(dto.getPaymentMethod()))
                             .quantity(dto.getQuantity())
                             .build();

      productTypeService.removeQuantityFromProduct(dto.getTypeId(), dto.getQuantity());

      productHistoryRepository.save(productHistory);

    }

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
