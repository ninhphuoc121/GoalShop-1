package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.Product;

import com.poly.entity.ReportCategory;

public interface ProductDao extends JpaRepository<Product, Integer>{

	

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(Integer cid);

	@Query("SELECT p FROM Product p WHERE p.Distcount > 0")
	List<Product> findByDis(Pageable page);

	@Query(value = "SELECT p FROM Product p WHERE p.Lastest = true")
	List<Product> findByLatest(Pageable page);

		@Query("SELECT p FROM Product p WHERE p.Special = true")
	List<Product> findBySpecial(Pageable page);

		@Query("SELECT p FROM Product p "
				+ " WHERE p.Name LIKE %:kw% OR p.category.name LIKE %:kw%")
		List<Product> findByKeywords(@Param("kw") String keywords);

		@Query("SELECT new ReportCategory(o.category.Category_id,o.category.name, sum(o.Unit_price), count(o)) "
				+ " FROM Product o "
				+ " GROUP BY o.category.Category_id ,o.category.name"
				+ " ORDER BY sum(o.Unit_price) DESC")
		List<ReportCategory> getReportCategory();

	
}
