package com.dh.webtest.controller;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




import com.dh.webtest.model.Brand;
import com.dh.webtest.model.Cart;
import com.dh.webtest.model.CartItem;
import com.dh.webtest.model.Category;
import com.dh.webtest.model.Customer;
import com.dh.webtest.model.Order;
import com.dh.webtest.model.OrderDetail;
import com.dh.webtest.model.Product;
import com.dh.webtest.model.ProductImage;
import com.dh.webtest.model.ShippingAddress;
import com.dh.webtest.model.StateVat;

import com.dh.webtest.repository.BrandRepository;
import com.dh.webtest.repository.CartRepository;
import com.dh.webtest.repository.CartItemRepository;
import com.dh.webtest.repository.CategoryRepository;
import com.dh.webtest.repository.CustomerRepository;
import com.dh.webtest.repository.OrderRepository;
import com.dh.webtest.repository.ProductImageRepository;
import com.dh.webtest.repository.OrderDetailRepository;
import com.dh.webtest.repository.ProductRepository;
import com.dh.webtest.repository.ShippingAddressRepository;
import com.dh.webtest.repository.StateVatRepository;





@RestController
public class ApplicationController {




@Autowired
BrandRepository brandRepository;

@Autowired
CartItemRepository cartItemRepository ;

@Autowired
CartRepository cartRepository ;

@Autowired
CustomerRepository customerRepository ;
@Autowired
OrderRepository orderRepository ;

@Autowired
OrderDetailRepository orderDetailRepository ;

@Autowired
ShippingAddressRepository shippingAddressRepository ;

@Autowired
ShippingAddressRepository shippingAddressRepository2;

@Autowired
ProductRepository productRepository ;

@Autowired
StateVatRepository stateVatRepository ;

@Autowired
CategoryRepository categoryRepository ;

@Autowired
ProductImageRepository productImageRepository;

@RequestMapping("/log")
public HashMap<String, String> getLogin() {



HashMap<String, String> returnParams = new HashMap<String, String>();

System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//    System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//    System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
//    System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
System.out.println(SecurityContextHolder.getContext().getAuthentication());

if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()==true)
{
  returnParams.put("status", "true"); 
  
  Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
  Boolean authority = authorities.contains(new SimpleGrantedAuthority("USER"));
  Boolean auth = true;
  System.out.println(authority);
  if(authority==auth)
    returnParams.put("role", "user");
  else
    returnParams.put("role", "admin");
}
else
{
  returnParams.put("status", "false");  
}



return returnParams;
}



@RequestMapping("/brands")
public List<Brand> getBrands() {
  return (List<Brand>) brandRepository.findAll();
} 


@RequestMapping("/cart")
public Cart getCart() {
	Customer customer = getCustomer();
	int cartid = customer.getCart().getCartId();
  return cartRepository.findOne(cartid);
} 



@RequestMapping("/cartitems")
public List<CartItem> getCartItem() {
  return (List<CartItem>) cartItemRepository.findAll();
}


@RequestMapping("/customers")
public List<Customer> getCustomers() {
  
  return (List<Customer>) customerRepository.findAll();
} 

@RequestMapping("/customers/one")
public Customer  getCustomer (){
  String userName = SecurityContextHolder.getContext().getAuthentication().getName();
  return  customerRepository.findByuserName(userName);
}

@RequestMapping("/orders")
public List<Order> getOrder() {
  return (List<Order>) orderRepository.findAll();
} 


@RequestMapping("/orderdetails")
public List<OrderDetail> getOrderDetail() {
  return (List<OrderDetail>) orderDetailRepository.findAll();
}

@RequestMapping("/products")
public List<Product> getProducts() {
  return (List<Product>) productRepository.findAll();
}

@RequestMapping("/products/{productId}")
public Product  getProduct (@PathVariable("productId") int productId){
  //String productName = SecurityContextHolder.getContext().getAuthentication().getName();
  return  productRepository.findOne(productId);
}

@RequestMapping("/viewproducts")
public List<Product> getviewProducts() {
  return (List<Product>) productRepository.findAll();
} 

@RequestMapping("/categories")
public List<Category> getCategories() {
  return (List<Category>) categoryRepository.findAll();
} 
@RequestMapping("/shippingaddress/{shippingId}")
public ShippingAddress getShippingAddress(@PathVariable("shippingId") int shippingId) {
  return  shippingAddressRepository2.findOne(shippingId);
}


@RequestMapping("/shippingaddresses")
public List<ShippingAddress> getShippingAddress() {
  
  return (List<ShippingAddress>) shippingAddressRepository.findAll();
}
@RequestMapping("/shippingaddresses/one")
public List<ShippingAddress> getShippingAddressOne() {
  String userName = SecurityContextHolder.getContext().getAuthentication().getName();
  Customer customer=  customerRepository.findByuserName(userName);
  int id=customer.getCustomerId();
  System.out.println("---------------->Customer object is :"+customer);
  return (List<ShippingAddress>) shippingAddressRepository.findByCustomer(customer);
}

@RequestMapping("/displaystate")
public List<StateVat> getStateVat() {
  return (List<StateVat>) stateVatRepository.findAll();
} 

@RequestMapping("/savecustomer")
public HashMap<String, Object> savecustomer(@RequestBody Customer customer) {
  HashMap<String, Object> returnParams = new HashMap<String, Object>();
  
  try {
    customerRepository.save(customer);
    returnParams.put("status", true);
  } catch (Exception e) {
    returnParams.put("status", false);
    returnParams.put("msg", "customer Addition Failed!!!!!!");
    

    
  }
  return returnParams;  

}

@RequestMapping("/addshippingaddress")
public HashMap<String, Object> addShippingaddress(@RequestBody ShippingAddress shippingaddress) {
  HashMap<String, Object> returnParams = new HashMap<String, Object>();
  try{
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    Customer customer=  customerRepository.findByuserName(userName);
    System.out.println("Customer Object:"+customer);
    int id=customer.getCustomerId();
    System.out.println("-----------------------> Customer Id:"+id);
    
    shippingaddress.setCustomer(customer);
    String state=shippingaddress.getState();
    System.out.println("------------------->State is:"+state);
    StateVat stateVat=(StateVat) stateVatRepository.findBystate(state);
    int stateId=stateVat.getStateId();
    System.out.println("------------------------> State Id :"+stateId);
    shippingaddress.setStateVat(stateVat);
    
    
    System.out.println(customer);
    
    shippingAddressRepository2.save(shippingaddress);
    returnParams.put("status", true);
  } catch (Exception e) {
    returnParams.put("status", false);
    returnParams.put("msg", "Shippingaddress Addition Failed!!!!!!");
    

    
  }
  return returnParams;  

}

@RequestMapping("/editshippingaddress")
public HashMap<String, Object> editShippingaddress(@RequestBody ShippingAddress shippingaddress) {
  HashMap<String, Object> returnParams = new HashMap<String, Object>();
  try{

    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    Customer customer=  customerRepository.findByuserName(userName);
    System.out.println("Customer Object:"+customer);
    int id=customer.getCustomerId();
    System.out.println("-----------------------> Customer Id:"+id);
    
    shippingaddress.setCustomer(customer);
    String state=shippingaddress.getState();
    System.out.println("------------------->State is:"+state);
    StateVat stateVat=(StateVat) stateVatRepository.findBystate(state);
    int stateId=stateVat.getStateId();
    System.out.println("------------------------> State Id :"+stateId);
    shippingaddress.setStateVat(stateVat);
    System.out.println(customer);

    
      /*String userName = SecurityContextHolder.getContext().getAuthentication().getName();
      Customer customer=  customerRepository.findByuserName(userName);*/
      
        /*int id=customer.getCustomerId();
  */
        
        shippingaddress.setCustomer(customer);
      /*String state=shippingaddress.getState();
      
      StateVat stateVat=(StateVat) stateVatRepository.findBystate(state);
      int stateId=stateVat.getStateId();*/
      
      shippingaddress.setStateVat(stateVat);
      
      
      
      

      shippingAddressRepository2.save(shippingaddress);
      returnParams.put("status", true);
    } catch (Exception e) {
      returnParams.put("status", false);
      returnParams.put("msg", "Shippingaddress Addition Failed!!!!!!");
    }
    return returnParams;  

  }
  
  
/*  @RequestMapping("/saveproduct")
  public HashMap<String, Object> saveproduct(@RequestBody Product product, @RequestParam("file") MultipartFile file) throws IOException {
    HashMap<String, Object> returnParams = new HashMap<String, Object>();
    
    try {
    	byte[] b = file.getBytes();
    	String imageFile = StringUtils.newStringUtf8(Base64.encodeBase64(b));
    	System.out.println(imageFile);
    	product.setImage(imageFile);
    	product.setExtn("jpg");
      System.out.println(product.getCategory());
      productRepository.save(product);
      returnParams.put("status", true);
    } catch (Exception e) {
      returnParams.put("status", false);
      returnParams.put("msg", "product Addition Failed!!!!!!");   
    }
    return returnParams;  

  }*/


  @RequestMapping("/saveproduct")
  public HashMap<String, Object> saveproduct(@RequestBody Product product) throws IOException {
    HashMap<String, Object> returnParams = new HashMap<String, Object>();
    
    try {
//    	byte[] b = file.getBytes();
//    	String imageFile = StringUtils.newStringUtf8(Base64.encodeBase64(b));
//    	System.out.println(imageFile);
     // System.out.println(product.getCategory());
      productRepository.save(product);
      returnParams.put("status", true);
    } catch (Exception e) {
      returnParams.put("status", false);
      returnParams.put("msg", "product Addition Failed!!!!!!");   
    }
    return returnParams;  

  }
  
  
  
  @RequestMapping("/savedetails")
  public HashMap<String, Object> savedetails(@RequestBody Customer customer) {
    HashMap<String, Object> returnParams = new HashMap<String, Object>();
    
    try {
      customerRepository.save(customer);
      returnParams.put("status", true);
    } catch (Exception e) {
      e.printStackTrace();
      returnParams.put("status", false);
      returnParams.put("msg", "Details Addition Failed!!!!!!");
      

      
    }
    return returnParams;
  }
  
 /* @RequestMapping("/addtocart")
  public HashMap<String, Object> addtoCart(@RequestBody CartItem cartitem) {
  	HashMap<String, Object> returnParams = new HashMap<String, Object>();
  try
  {
  	Customer customer= getCustomer();
  	Cart cartcheck = customer.getCart();
  	if(cartcheck != null)
  	{
  int cartid = customer.getCart().getCartId();
  Cart cart = cartRepository.findOne(cartid);
   	cartitem.setCart(cart);
   	
  	}
  	List<CartItem> cartitems = cart.getCartitem();
  	Iterator<CartItem> it = cartitems.iterator();
  	while(it.hasNext()){
  		CartItem cartitem= (CartItem)it.next();
  		Product product = cartitem.getProduct();
  		product.setCartitem(cartitem);
  		cartitem.setCart(cart);
  	}
  		else
  		{
  			Cart cart1 = customer.getCart();
  			cartitem.setCart(cart1);
  }
  	
  	
  //	cart.setCustomer(getCustomer());
  	cartItemRepository.save(cartitem);
  	returnParams.put("status", true);
  } catch (Exception e) {
  	e.printStackTrace();
  		returnParams.put("status", false);
  		returnParams.put("msg", "Cart Addition Failed!");
  	
  	
  }
  return returnParams;
  }
  }
*/
/*  
  @RequestMapping(value = "/upload")
  public HashMap<String, Object> handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException{
    File convertedFile=convert(file);
System.out.println(convertedFile);
Product prodcut=new Product();
//int id=product.getProductId();
HashMap<String, Object> returnParams = new HashMap<String, Object>();
byte[] b = file.getBytes();
String imageFile = StringUtils.newStringUtf8(Base64.encodeBase64(b));
System.out.println(imageFile);

Product uploadFile = new Product();
uploadFile.setImage(imageFile);
uploadFile.setExtn("jpg");
//uploadFile.setProduct(product);
productRepository.save(uploadFile);
System.out.println("Image Successfully Manipulated!");
return returnParams;
}
}*/


  /*@Autowired
    ProductDAO productDAO;
     
  
  @RequestMapping("/product")
    public String index(Map<String, Object> map) {
        try {
            map.put("product", new Product());
            map.put("productList", productDAO.list());
        }catch(Exception e) {
            e.printStackTrace();
        }
 
        return "products";
      }*/
      
  /*@RequestMapping(value="/image/:id", produces="image/png")
  public String getImage(){
    return null;
    
  }*/
  

  @RequestMapping("/cartcheck")
  public HashMap<String, String> cartCheck() {

  HashMap<String, String> returnParams = new HashMap<String, String>();

  Customer customer= getCustomer();
	Cart cartcheck = customer.getCart();
	if(cartcheck != null)
		returnParams.put("status", "true");
  else
    returnParams.put("status", "false");  
  return returnParams;
  }
  
  
  
  @RequestMapping("/addcartitem")
  public HashMap<String, Object> addCartitem(@RequestBody CartItem cartitem) {
  	HashMap<String, Object> returnParams = new HashMap<String, Object>();
  try
  {
  	Customer customer= getCustomer();
  	Cart cartcheck = customer.getCart();
  	if(cartcheck != null)
  	{
  int cartid = customer.getCart().getCartId();
  Cart cart = cartRepository.findOne(cartid);
   	cartitem.setCart(cart);
   	
  	}
  	
  		/*else
  		{
  			Cart cart1 = customer.getCart();
  			cartitem.setCart(cart1);
  }*/
  	
  	
  //	cart.setCustomer(getCustomer());
  	cartItemRepository.save(cartitem);
  	returnParams.put("status", true);
  } catch (Exception e) {
  	e.printStackTrace();
  		returnParams.put("status", false);
  		returnParams.put("msg", "Cart Addition Failed!");
  	
  	
  }
  return returnParams;
  }
  
  
  
  @RequestMapping("/addcart")
  public HashMap<String, Object> addCart(@RequestBody Cart cart) {
  	HashMap<String, Object> returnParams = new HashMap<String, Object>();
 try
  {
  	
   	
  	
  	List<CartItem> cartitems = cart.getCartitem();
  	Iterator<CartItem> it = cartitems.iterator();
  	while(it.hasNext()){
  		CartItem cartitem= (CartItem)it.next();
  		/*Product product = cartitem.getProduct();
  		product.setCartitem(cartitem);*/
  		cartitem.setCart(cart);
  	}
  		
  cart.setCustomer(getCustomer());
  	
  	
  
  	cartRepository.save(cart);
  	returnParams.put("status", true);
  } catch (Exception e) {
  	e.printStackTrace();
  		returnParams.put("status", false);
  		returnParams.put("msg", "Cart Addition Failed!");
  	
  	
  }
  
  return returnParams;
  }
  
  @RequestMapping("/calculatevat")
  public HashMap<String, Double> calculateVat(@RequestBody ShippingAddress shippingaddress) {
  	HashMap<String, Double> returnParams = new HashMap<String, Double>();
  //	Cart cart=new Cart();
  	Cart cart = getCustomer().getCart();
  	float total=cart.getTotalcost();
  	String state = shippingaddress.getState();
 StateVat statevat= stateVatRepository.findBystate(state);  
 StateVat country = stateVatRepository.findBystate("india");
	double cst = (country.getVatPercent()*total)/100;
	double vat= (statevat.getVatPercent()*total)/100;
	double finalprice = total+cst+vat;
	returnParams.put("cst", cst);
	returnParams.put("vat", vat);
	returnParams.put("finalprice", finalprice);


	return returnParams;
  	
  }
}

  