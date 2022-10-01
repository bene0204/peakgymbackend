package com.benem.peakgym.product_type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, String> {
  List<ProductTypeEntity> findAllByNameContainingIgnoreCase(String name);
}
