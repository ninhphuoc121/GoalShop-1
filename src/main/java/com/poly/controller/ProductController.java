package com.poly.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDao;
import com.poly.entity.Product;
import com.poly.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductDao pdao;
	
	@RequestMapping("/product/list")
	public String list(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 9;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			Page<Product> list = pdao.findAll(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id")Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item",item);
		return "product/detail";
	}
	@RequestMapping("/product/discount")
	public String discount(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 6;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findByDis(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/discount";
	}
	@RequestMapping("/product/latest")
	public String latest(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 6;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findByLatest(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/latest";
	}
	@RequestMapping("/product/special")
	public String special(Model model, @RequestParam("cid") Optional<Integer> cid 
			, @RequestParam("page") Optional<Integer> page) {
		int x = page.orElse(0);
		if (x < 0) {
		}
		model.addAttribute("page", x);
		int size = 6;
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		} else {

			List<Product> list = pdao.findBySpecial(PageRequest.of(x, size));
			model.addAttribute("items", list);
		}

		return "product/special";
	}
	@RequestMapping("/product/list-by-keywords")
	public String list(Model model, @RequestParam("keywords") String keywords) {
		List<Product> list = pdao.findByKeywords(keywords);
		model.addAttribute("list", list);
		return "product/list";
	}

}
