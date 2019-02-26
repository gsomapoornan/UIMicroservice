package com.visualpath.UIMicroservice.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.visualpath.UIMicroservice.Model.Authuser;

public interface AuthuserRepo extends CrudRepository<Authuser, Integer>{
	Authuser findByName(String name);

}
