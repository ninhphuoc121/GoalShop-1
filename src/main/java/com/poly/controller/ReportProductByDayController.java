package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.dao.ProductDao;
import com.poly.dao.ReportProductbyDayDao;
import com.poly.entity.ReportCategory;
import com.poly.entity.ReportProductbyDay;

import java.io.Serializable;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.ui.Model;	
@Controller
public class ReportProductByDayController {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	@Autowired
	ReportProductbyDayDao DAO;
	
	@RequestMapping("/reportProductByDay" )
    public String getPieChart(Model model ,@RequestParam(value= "minday" , required = false)@DateTimeFormat(pattern="yyyy/MM/dd") Date minday,
    		@RequestParam(value = "maxday", required = false)@DateTimeFormat(pattern="yyyy/MM/dd") Date maxday) {
    	   Map<String, Double > graphData = new TreeMap<>();
    	if(minday==(null)  && maxday==(null)) {
    		Date min = new Date(2001,01,01);
    		Date max = new Date(3000,01,01);
    		List<ReportProductbyDay>  report =  DAO.reportProdctByDay(min,max);
            for(int i=0 ; i<report.size();i++) {
            	graphData.put(report.get(i).getProduct_name().toString(),report.get(i).getSum());
            }
    	}
    	else {
    	List<ReportProductbyDay>  report =  DAO.reportProdctByDay(minday,maxday);
        for(int i=0 ; i<report.size();i++) {
        	graphData.put(report.get(i).getProduct_name().toString(),report.get(i).getSum());
        }
    	}
        model.addAttribute("chartData", graphData);
        return "char/index2";
    }
  
   
}
