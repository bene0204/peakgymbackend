package com.benem.peakgym.product_type;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductTypeController {

  @Autowired
  private ProductTypeService productTypeService;

  @PostMapping("api/producttype/add")
  public ProductTypeEntity addProductType(@Valid @RequestBody ProductTypeEntity productTypeEntity) {
    return productTypeService.addProductType(productTypeEntity);
  }

  @GetMapping("api/producttype/list")
  public List<ProductTypeEntity> getProductList(@Nullable @RequestParam("name") String name) {
      return  productTypeService.getProductList(name);
  }
}
