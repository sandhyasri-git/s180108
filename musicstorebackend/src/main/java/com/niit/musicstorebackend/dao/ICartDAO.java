package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.Cart;

public interface ICartDAO {

	public boolean save(Cart cart);

	public boolean update(Cart cart);

	public boolean delete(Cart cart);

	public Cart getbyid(int id);
	public Cart get(int id,int Cartid);
	
	public List<Cart> listcartproducts(int id);
	
	public int totalproducts(int id);

	public int totalprice(int id);
	
	
	public List<Cart> list();

	public boolean resetCart(int id);
    
	
	Cart getCart(int id);
	
	Cart getCartWithUserId(Integer id);

}
