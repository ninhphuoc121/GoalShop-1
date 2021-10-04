package com.poly.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class Account {
	@Id
	String Username;
	String Password;
	String Fullname;
	String Email;
	String Photo;
	String Phone;
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> Orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<Authority> Authorities;
}
