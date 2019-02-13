package com.visualpath.UIMicroservice.Services;

import java.util.List;

import com.visualpath.UIMicroservice.Model.Product;
import com.visualpath.UIMicroservice.Model.User;

public interface UIService {
	
	public User getUserByID(long id);
	public List<Product> getProducts();

}
