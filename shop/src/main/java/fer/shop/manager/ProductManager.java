package fer.shop.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import fer.shop.dao.ProductDao;
import fer.shop.entity.Product;
import fer.shop.resource.HibernateSessionManager;

@Service
public class ProductManager {
	ProductDao productDao;
	public void setProductDao(ProductDao productDao){
		this.productDao = productDao;
	}
	
	public void createProduct(Product product){
		try{
			HibernateSessionManager.beginTransaction();
			productDao.save(product);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in find all products");
		}
	}
	
	public void deleteProduct(Product product){
		try{
			HibernateSessionManager.beginTransaction();
			productDao.delete(product);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in find all products");
		}
	}
	
	public void updateProduct(Product product){
		try{
			HibernateSessionManager.beginTransaction();
			productDao.update(product);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in find all products");
		}
	}
	
	public List<Product> getAllProducts(){
		List<Product> allProducts = new ArrayList<Product>();
		try{
			HibernateSessionManager.beginTransaction();
			allProducts = productDao.findAll(Product.class);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in find all products");
		}
		return allProducts;
	}
	
	public List<Product> getProductsByName(String name){
		List<Product> products = new ArrayList<Product>();
		try{
			HibernateSessionManager.beginTransaction();
			products = productDao.findByName(name);
			HibernateSessionManager.commitTransaction();
		}catch(HibernateException ex){
			System.out.println("Error in find products by name");
		}
		return products;
	}
	
}
