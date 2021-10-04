package com.poly.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDao;
import com.poly.dao.AuthorityDao;
import com.poly.dao.ProductDao;
import com.poly.entity.Product;
import com.poly.service.AccountService;
import com.poly.service.AuthorityService;
import com.poly.service.ProductService;



@Service
public class ProductServiceImlq implements ProductService {
	@Autowired
	private ProductDao pdao;
	@Override
	public List<Product> findAll() {
		
		return pdao.findAll();
	}

	@Override
	public Product findById(Integer id) {
		
		return pdao.findById(id).get();
	}



	@Override
	public List<Product> findByCategoryId(Integer cid) {
		// TODO Auto-generated method stub
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return pdao.save(product);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		 pdao.deleteById(id);;
	}

	@Override
	public List<Product> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}



	

	

	
}
