package com.visualpath.UIMicroservice.Services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.visualpath.UIMicroservice.Model.Product;

@FeignClient(name="${product.servicename}")
public interface ProductRemoteCallService {
	
	@GetMapping("/products")
	public List<Product> getProducts();

}
