package com.poly.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Categories")
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer Category_id;
	String name;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> Products;
}
