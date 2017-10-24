package com.niit.musicstorebackend.dao;

import java.util.List;

import com.niit.musicstorebackend.model.Supplier;

public interface ISupplierDAO {
	
	public boolean saveorUpdate(Supplier supplier);
	public boolean delete(Supplier supplier);
	public Supplier get(int id);
	public List<Supplier> list();
	public boolean update(Supplier supplier);


}
