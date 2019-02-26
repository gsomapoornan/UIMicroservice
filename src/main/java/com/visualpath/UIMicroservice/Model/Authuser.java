package com.visualpath.UIMicroservice.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@XmlRootElement
@Table(name="AUTHUSER")
public class Authuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int id;
	private String name;
	private String password;
		
	
	/*@JoinTable(name = "USER_ROLE",
			joinColumns = @JoinColumn(name = "USER_ID", unique=false),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID", unique=false))
			@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
			@LazyCollection(LazyCollectionOption.FALSE)*/
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLE",joinColumns= {@JoinColumn(name="USER_ID",referencedColumnName = "USER_ID")},
			inverseJoinColumns = {@JoinColumn( name="ROLE_ID",referencedColumnName = "ROLE_ID")})	
	private Set<Role> roles;
	
	

	public Authuser(Authuser authuser) {
		super();
		this.id = authuser.id;
		this.name = authuser.name;
		this.password = authuser.password;
		this.roles = authuser.roles;
	}

	public Authuser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Authuser(int id, String name, String password, Set<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/*@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "USER_ROLE",
	joinColumns = @JoinColumn(name = "USER_ID", unique=false),
	inverseJoinColumns = @JoinColumn(name = "ROLE_ID", unique=false))
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	@LazyCollection(LazyCollectionOption.FALSE)*/
	public Set<Role> getRoles() {
		
		Set<Role> tempSet=new HashSet<Role>();
		tempSet.add(new Role(1,"user"));
		return tempSet;
	
	}

	public void setRoles(Set<Role> roles) {
		
		this.roles = roles;
	}
	
	

	
	
}
