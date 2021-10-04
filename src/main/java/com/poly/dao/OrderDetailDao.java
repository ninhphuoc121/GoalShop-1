package com.poly.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Account;
import com.poly.entity.Authority;
import com.poly.entity.Category;
import com.poly.entity.OrderDetail;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer>{
	@Query("SELECT o FROM OrderDetail o WHERE o.order.Order_id = ?1")
	List<OrderDetail> findByOrderID(Integer orderid);
}
