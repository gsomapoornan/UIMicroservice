package com.visualpath.UIMicroservice.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.visualpath.UIMicroservice.Model.Product;
import com.visualpath.UIMicroservice.Model.User;

@Service("uiService")
public class UIServiceImpl implements UIService{
	
	@Autowired
	UserRemoteCallService userRemoteClassService;
	
	@Autowired
	ProductRemoteCallService productRemoteCallService;
	
	@Autowired
	private LoadBalancerClient loadBalancer;
	

	@Override
	public User getUserByID(long id) {		
		return userRemoteClassService.getUserByID(id);
	}

	@Override
	@HystrixCommand(fallbackMethod="getDefaultProducts",commandKey="prodHystrixCommand")
	public List<Product> getProducts() {
		//return productRemoteCallService.getProducts();
		RestTemplate restTemplate = new RestTemplate();
		   ServiceInstance serviceInstance=this.loadBalancer.choose("${product.servicename}");
		   String url="http://" + serviceInstance.getHost()+ ":" + serviceInstance.getPort() + "/products" ;			
		      
		   ResponseEntity<List<Product>> response = restTemplate.exchange(
				   url,
				   HttpMethod.GET,
				   null,
				   new ParameterizedTypeReference<List<Product>>(){});
				 List<Product> prodList = response.getBody();
				 return prodList;
	}
	
	public List<Product> getDefaultProducts() {
		List<Product> prodList =new ArrayList<Product>();
		prodList.add(new Product(11,"Laptop",666));
		return prodList;
		
	}
	
}
