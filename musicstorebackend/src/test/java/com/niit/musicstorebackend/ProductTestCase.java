package com.niit.musicstorebackend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.musicstorebackend.dao.ProductDAO;
import com.niit.musicstorebackend.model.Product;

public class ProductTestCase {
	
	
	static AnnotationConfigApplicationContext context;
	static ProductDAO productDAO;
	static Product product;
	
	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com");
		context.refresh();
		productDAO=(ProductDAO)context.getBean("productDAO");
	}

	@Test
	public void test() {
		product=new Product();
		product.setProdname("Veena");
		product.setQty(10);
		product.setPrice(12000);
		//assertEquals("inserted", true,productDAO.saveProduct(product));
		productDAO.saveProduct(product);
		assertEquals("inserted",true);
		
	}

}
