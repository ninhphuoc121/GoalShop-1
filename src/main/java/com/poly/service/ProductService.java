package com.poly.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.poly.entity.Product;

public interface ProductService {

	List<Product> findByCategoryId(Integer integer);

	List<Product> findAll();


	Product findById(Integer id);




	Product create(Product product);

	Product update(Product product);

	void delete(Integer id);

	List<Product> findByNameContaining(String name);

	List<Product> findByKeywords(String keywords);

	


}
