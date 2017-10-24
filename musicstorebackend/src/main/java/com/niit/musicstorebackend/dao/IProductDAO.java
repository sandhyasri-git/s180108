package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.Product;

public interface IProductDAO {
	
	public void saveProduct(Product product);
public boolean delete(Product product);
	
	public Product get(int id);
	
	public List<Product> list();

}
