package com.benem.peakgym.product_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductHistoryController {

  @Autowired
  private ProductHistoryService productHistoryService;

  @PostMapping("api/product/sell")
  public ProductHistoryEntity sellProduct(@RequestParam("typeId") String typeId,
                                          @RequestParam("buyerId") String buyerId,
                                          @RequestParam("quantity") Integer quantity){
      return productHistoryService.sellProduct(typeId, buyerId, quantity);
  }
}
