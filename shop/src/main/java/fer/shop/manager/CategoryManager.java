package fer.shop.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.shop.dao.CategoryDao;
import fer.shop.dao.ProductDao;
import fer.shop.entity.Category;
import fer.shop.entity.Product;

import java.util.*;

@Service
public class CategoryManager {
	@Autowired
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	@Autowired
	private ProductDao productDao;
	public void setUserDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public void createCategory(String name, String description){
		Category category = new Category();
		category.setCatName(name);
		category.setCatDescription(description);
		categoryDao.save(category);
	}
	
	public void deleteCategory(Category category){
		categoryDao.delete(category);
	}
	
	public void updateCategory(Category category){
		categoryDao.save(category);
	}
	
	public Category getCategory(Long categoryId){
		return categoryDao.findOne(categoryId);
	}
	
	public List<Category> getCategories(){
		return (List<Category>) categoryDao.findAll();
	}
	
	public void categoryAddProduct(Long catId, Long prodId){
		System.out.println("Attempting to add product "+prodId+"to category "+catId);
		Category category = categoryDao.findOne(catId);
		category.getProducts().add(productDao.findOne(prodId));
		categoryDao.save(category);
	}
	
	public void categoryRemoveProduct(Long catId, Long prodId){
		categoryDao.findOne(catId).getProducts().remove(productDao.findOne(prodId));
	}
	
	public void updateProductCategories(Long prodId, Set<Long> catIds){
		Product prod = productDao.findOne(prodId);
		prod.getProductCategories().forEach(cat -> {cat.getProducts().remove(prod);categoryDao.save(cat);});
		catIds.stream().forEach(c -> {Category cat = categoryDao.findOne(c);cat.getProducts().add(prod);categoryDao.save(cat);});
		productDao.save(prod);
	}
}
