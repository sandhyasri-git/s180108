package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.CartItem;

public interface ICartItemDAO {
	public boolean addCartItem(CartItem cartItem);
	public List<CartItem> getAll();
	public boolean deleteCartItem(CartItem cartItem);
	public CartItem getCartItem(int id);
	public boolean deleteAll(int cart_id);
	public CartItem getExistingCartItemCount(int prodid, int cart_id);
	public boolean updateCartItem(CartItem cartItem);


}
