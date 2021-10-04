package com.poly.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity @Table(name = "Products")
public class Product {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer Product_id;
	String Name;
	String Image1;
	String Image2;
	String Image3;
	String Image4;
	String Image5;
	String Image6;
	String Image7;
	String Detail;
	String Description;
	Double Unit_price;
	Integer Quantity;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date Product_date;
	Float Distcount;
	Boolean Status;
	Boolean Special = false;
	Boolean Lastest = false;
	@ManyToOne
	@JoinColumn(name = "Category_id")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
}
