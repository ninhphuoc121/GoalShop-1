package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.dao.ProductDao;
import com.poly.entity.ReportCategory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.ui.Model;	
@Controller
@RequestMapping("/")
public class RootController {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	@Autowired
	ProductDao DAO;
	
    @GetMapping
    public String getPieChart(Model model) {
    	List<ReportCategory>  report =  DAO.getReportCategory();
        Map<String, Double> graphData = new TreeMap<>();
//        graphData.put("phuoc", 11);
//        graphData.put("tan ", 34);
//        graphData.put("hiang", 54);
//        graphData.put("tam ", 43);
        for(int i=0 ; i<report.size();i++) {
        	graphData.put(report.get(i).getCategoty_name().toString(),report.get(i).getSum());
        }
        model.addAttribute("chartData", graphData);
        return "char/index";
    }
  
   
}
