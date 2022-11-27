package com.benem.peakgym.product_type;

import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

  private final ProductTypeRepository productTypeRepository;

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

  public void deleteProduct(String typeId) {
    this.productTypeRepository.deleteById(typeId);
  }

  public ProductTypeEntity updateProduct(String typeId, ProductTypeEntity product) {
    var saved = findProductTypeById(typeId);

    if (!Objects.equals(saved.getName(), product.getName())) {
      saved.setName(product.getName());
    }
    if (!Objects.equals(saved.getQuantity(), product.getQuantity())) {
      saved.setQuantity(product.getQuantity());
    }
    if (!Objects.equals(saved.getPrice(), product.getPrice())) {
      saved.setPrice(product.getPrice());
    }

    return productTypeRepository.save(saved);
  }


}
