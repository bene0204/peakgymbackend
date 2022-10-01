package com.benem.peakgym.product_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService {

  @Autowired
  private ProductTypeRepository productTypeRepository;

  public ProductTypeEntity addProductType(ProductTypeEntity productTypeEntity) {
    return productTypeRepository.save(productTypeEntity);
  }

  public ProductTypeEntity findProductTypeById(String typeId) {
    return productTypeRepository.findById(typeId).get();
  }

  public void removeQuantityFromProduct(String typeId, Integer quantity) {
    var product = findProductTypeById(typeId);

    product.setQuantity(product.getQuantity() - quantity);

    productTypeRepository.save(product);
  }
  public List<ProductTypeEntity> getProductList(String name) {
    if (name == null) {
      return productTypeRepository.findAll();
    } else {
      return productTypeRepository.findAllByNameContainingIgnoreCase(name);
    }
  }
}
