package com.benem.peakgym.product_history;

import java.util.List;

import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductHistoryController {

    private final ProductHistoryService productHistoryService;

    @PostMapping("management/api/product/sell")
    public ProductHistoryEntity sellProduct(@RequestParam("typeId") String typeId,
                                          @RequestParam("buyerId") String buyerId,
                                          @RequestParam("paymentMethod") PAYMENT_METHOD paymentMethod,
                                          @RequestParam("quantity") Integer quantity) {
        return productHistoryService.sellProduct(typeId, buyerId, paymentMethod, quantity);
    }

    @GetMapping("management/api/product/listtransactions")
    public List<TransactionProjection> getTransactionsBetween(@RequestParam("fromDate") String fromDate,
                                                            @RequestParam("toDate") String toDate) {
        return productHistoryService.getTransactionsBetween(fromDate, toDate);
    }

    @GetMapping("management/api/product/sumbymethod")
    public Integer getSumOfTransactionsByPaymentMethod(@RequestParam("fromDate") String fromDate,
                                                     @RequestParam("toDate") String toDate,
                                                     @RequestParam("paymentMethod") PAYMENT_METHOD paymentMethod) {
        return productHistoryService.getSumOfTransactionsByPaymentMethod(fromDate, toDate, paymentMethod);
    }
}
