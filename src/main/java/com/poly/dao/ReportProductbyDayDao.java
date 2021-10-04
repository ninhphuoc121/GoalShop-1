package com.poly.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import com.poly.entity.ReportProductbyDay;




public interface ReportProductbyDayDao extends JpaRepository<ReportProductbyDay, Integer> {
	
	@Query(value="select pro.Product_id , pro.Name ,SUM(odt.Price * odt.Quantity ),SUM(odt.Quantity) from OrderDetails odt join Orders ord\r\n"
			+ "	on odt.Order_id = ord.Order_id \r\n"
			+ "	join Products pro \r\n"
			+ "	on odt.Product_id = pro.Product_id \r\n"
			+ "	where ord.CreateDate Between ?1 and  ?2 \r\n"
			+ "	Group by pro.Product_id  , pro.Name ; ",nativeQuery = true)
	List<ReportProductbyDay> reportProdctByDay(@DateTimeFormat(pattern="yyyy/MM/dd")Date MinDay , 
												@DateTimeFormat(pattern="yyyy/MM/dd")Date MaxDay );
	
}
