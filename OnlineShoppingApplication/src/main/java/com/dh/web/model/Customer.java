package com.dh.web.model;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dh.web.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int customerId;

	@Column(name = "firstname")
	String firstName;
	
	
	@Column(name = "lastname")
	String lastName;
	


	@Column(name = "emailId")
	String emailId;
	
	@Column(name = "username")
	String userName;
	
	@Column(name = "password")
	String password;
	
	
	@Column(name = "role")
	String role;
	
	


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}



	@OneToMany(mappedBy = "customer",  orphanRemoval = true)
	List<ShippingAddress> shippingAddress;
	
	
	@OneToOne(mappedBy = "customer", orphanRemoval = true)
	Cart cart;

	@OneToMany(mappedBy = "customer", orphanRemoval = true)
	List<Order> order;
	
	

	

	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	/*public String getPassword(String password) {

		
		//String passwordCode= BcryptDecoder(password);
		return password;
	}
*/

	public void setPassword(String password) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		
		
		this.password = hashedPassword;
	}
	
	/*public String BcryptDecoder(String password){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        	if( encoder.matches(password, getPassword(password))){
        	String status = "true";	
        	//userService.deactivateUserByID(customer.getCustomerId());
        //   RequestAttributes.addAttribute("successmsg", "Your account has been deactivated successfully.");
        }else{
            //redirectAttributes.addFlashAttribute("errormsg", "Email or Password is incorrect");
        }return password;
	}*/
	

	public List<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress() {
		this.shippingAddress = shippingAddress;
	}

	/*public void setAuthority(List<Authority> authority) {
		this.authority = authority
	}
	public List<Authority> getAuthority() {
		return authority;
	}*/

	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public List<Order> getOrder() {
		return order;
	}


	public void setOrder(List<Order> order) {
		this.order = order;
	}
	


	@Override
	public String toString() {
		String str = this.customerId+ " " + this.firstName +" "+this.lastName+ " "+this.emailId+    " " + this.userName + " " + this.password+ "\n";

		return str;

	}


	public String getPassword() {
		return password;
	}
	
	


}


