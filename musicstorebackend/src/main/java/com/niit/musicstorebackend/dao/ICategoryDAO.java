package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.Category;

public interface ICategoryDAO {
	
	public void saveCategory(Category category);
	public List<Category>getCategories();
	public boolean delete(Category category);
	public void deleteCategory(int id);
	public Category get(int id);
	public boolean update(Category category);

}
