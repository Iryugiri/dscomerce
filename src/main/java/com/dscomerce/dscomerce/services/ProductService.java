package com.dscomerce.dscomerce.services;

import com.dscomerce.dscomerce.dto.ProductDTO;
import com.dscomerce.dscomerce.entities.Product;
import com.dscomerce.dscomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        return new ProductDTO( productRepository.findById(id).get());
    }


    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product entity = new Product();
        copyDtoToEntity(productDTO, entity);
        entity = productRepository.save(entity);
        return new ProductDTO( entity );
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product entity = productRepository.getReferenceById(id);
        copyDtoToEntity(productDTO, entity);
        entity = productRepository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private void copyDtoToEntity(ProductDTO productDTO, Product entity) {
        entity.setName( productDTO.getName());
        entity.setDescription( productDTO.getDescription() );
        entity.setPrice( productDTO.getPrice());
        entity.setImgUrl( productDTO.getImgUrl());
    }

}
