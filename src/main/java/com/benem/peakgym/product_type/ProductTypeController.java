package com.benem.peakgym.product_type;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public List<ProductTypeEntity> getProductList(@RequestParam(value = "name", required = false) String name) {
      return  productTypeService.getProductList(name);
  }

  @PatchMapping("admin/api/producttype/update/{typeId}")
  public ProductTypeEntity updateProduct(@PathVariable("typeId") String typeId, @RequestBody ProductTypeEntity product) {
    return productTypeService.updateProduct(typeId, product);
  }


  @DeleteMapping("admin/api/producttype/delete/{typeId}")
  public void deleteProduct(@PathVariable("typeId") String typeId) {
    productTypeService.deleteProduct(typeId);
  }
}
