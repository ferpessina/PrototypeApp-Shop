package fer.shop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fer.shop.dto.ProductDto;
import fer.shop.entity.Product;
import fer.shop.manager.ProductManager;

@RestController
public class ProductController {
//	@Autowired
//	private UserManager userManager;
//	public void setUserManager(UserManager userManager) {
//		this.userManager = userManager;
//	}
	@Autowired
	private ProductManager productManager;
	public void setUserManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	
	@RequestMapping(value = "/api/users/{userId}/products", produces = "application/json", method = GET)
	public Set<ProductDto> getUserProducts(@PathVariable("userId") Long id) {
		System.out.println("Getting user products");
		Set<ProductDto> products = productManager.getByUserId(id);
		products.forEach(p -> System.out.println(p.getProductName()));
		return products;
	}
	
	@RequestMapping(value = "/api/products/{productId}", produces = "application/json", method = GET)
	public ProductDto getProduct(@PathVariable("productId") Long id) {
		System.out.println("Getting product");
		ProductDto product = new ProductDto(productManager.getProduct(id));
		return product;
	}
	
	@RequestMapping(value = "/api/products/update", method = POST, consumes = "application/json")
	public String updateUser(@RequestBody ProductDto product) {
		System.out.println(product.getProductName());
		System.out.println(product.getProductDescription());
		System.out.println(product.getProductPrice());
		System.out.println(product.getProductState());
		if(product.getProductName().length()<1 || product.getProductDescription().length()<1 || product.getProductState().length()<1 || product.getProductPrice()==null){
			return "Please complete all fields";
		}
		// TODO This should be a proper response object with better formatted
		// output / information similar to the loan request in the future
		String response = "product updated successfully!";
		productManager.updateProduct(product);
		return response;
	}
	
	@RequestMapping(value = "/api/products/create", method = POST, consumes = "application/json")
	public Long createProduct(@RequestBody ProductDto product) {

		System.out.println(product.getProductName());
		System.out.println(product.getProductDescription());
		System.out.println(product.getProductPrice());
		System.out.println(product.getProductState());
		System.out.println(product.getOwnerId());
		if(product.getProductName().length()<1 || product.getProductDescription().length()<1 || product.getProductState().length()<1 || product.getProductPrice()==null || product.getOwnerId()==null){
			return null;
		}
		// TODO This should be a proper response object with better formatted
		// output / information similar to the loan request in the future
		Product prod = productManager.addProduct(product);
		return prod.getProductId();
	}
	
	@RequestMapping(value = "/api/products/delete", method = POST)
	public String delete(@RequestParam("productid") Long id) {
		System.out.println("Deleting "+id);
		Product product = productManager.getProduct(id);
		if(product != null){
			productManager.delete(product);
		}else{
			System.out.println("product not found");
		}
		return "Product Deleted";
	}

}
