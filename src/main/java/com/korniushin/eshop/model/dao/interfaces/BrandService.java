package com.korniushin.eshop.model.dao.interfaces;

import com.korniushin.eshop.DTO.BrandDTO;
import com.korniushin.eshop.model.dao.interfaces.baseInterface.DAO;
import com.korniushin.eshop.model.entities.Brand;

import java.util.List;

public interface BrandService extends DAO<Brand> {
  List<BrandDTO> getBrands();
}
