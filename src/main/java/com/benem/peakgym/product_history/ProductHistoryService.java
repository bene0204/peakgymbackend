package com.benem.peakgym.product_history;

import com.benem.peakgym.product_type.ProductTypeService;
import com.benem.peakgym.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductHistoryService {

  @Autowired
  private ProductHistoryRepository productHistoryRepository;

  @Autowired
  private ProductTypeService productTypeService;

  @Autowired
  private UserService userService;
  @Transactional
  public ProductHistoryEntity sellProduct(String typeId, String buyerId, Integer quantity) {
    var buyer = userService.findUserById(buyerId);
    var type = productTypeService.findProductTypeById(typeId);

    var productHistory = ProductHistoryEntity.builder()
                           .product(type)
                           .buyer(buyer)
                           .price(type.getPrice() * quantity)
                           .build();

    productTypeService.removeQuantityFromProduct(typeId, quantity);

    return productHistoryRepository.save(productHistory);
  }
}
