package com.korniushin.eshop.model.dao.implementations;

import com.korniushin.eshop.DTO.BrandDTO;
import com.korniushin.eshop.model.dao.interfaces.BrandService;
import com.korniushin.eshop.model.dao.repositories.BrandRepository;
import com.korniushin.eshop.model.entities.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImplementation implements BrandService {
    final private BrandRepository brandRepository;
    @Override
    public List<Brand> all() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        Brand newBrand = brandRepository.findById(brand.getId()).get();
        newBrand.setTitle(brand.getTitle());
        newBrand.setProducts(brand.getProducts());
        return brandRepository.save(newBrand);
    }

    @Override
    public boolean deleteById(Long id) {
        if(brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public List<BrandDTO> getBrands () {
        List<BrandDTO> brands = brandRepository.findAll().stream()
                .map(brand -> BrandDTO.builder()
                        .id(brand.getId())
                        .title(brand.getTitle())
                        .build())
                .toList();
        return brands;
    }
}
