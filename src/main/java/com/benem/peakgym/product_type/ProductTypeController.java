package com.benem.peakgym.product_type;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductTypeController {

  private final ProductTypeService productTypeService;

  @PostMapping("admin/api/producttype/add")
  public ProductTypeEntity addProductType(@Valid @RequestBody ProductTypeEntity productTypeEntity) {
    return productTypeService.addProductType(productTypeEntity);
  }

  @GetMapping("management/api/producttype/list")
  public List<ProductTypeEntity> getProductList(@Nullable @RequestParam("name") String name) {
      return  productTypeService.getProductList(name);
  }
}
