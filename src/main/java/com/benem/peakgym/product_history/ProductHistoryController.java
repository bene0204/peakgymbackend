package com.benem.peakgym.product_history;

import java.util.List;

import com.benem.peakgym.product_history.dto.SellProductDTO;
import com.benem.peakgym.product_history.projections.TransactionProjection;
import com.benem.peakgym.util.enums.PAYMENT_METHOD;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductHistoryController {

    private final ProductHistoryService productHistoryService;

    @PostMapping("management/api/product/sell/{buyerId}")
    public void sellProduct(@PathVariable("buyerId") String buyerId,
                            @RequestBody List<SellProductDTO> productDTOS) {
         productHistoryService.sellProduct(buyerId, productDTOS);
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
