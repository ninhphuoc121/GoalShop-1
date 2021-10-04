package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{

	@Query("SELECT o FROM Order o WHERE o.account.Username=?1 order by o.id desc ")
	List<Order> findByUsername(String username);
	
	@Query(value="Select * from Orders order by Orders.id desc",nativeQuery = true)
	List<Order> findByAllDesc();

}
