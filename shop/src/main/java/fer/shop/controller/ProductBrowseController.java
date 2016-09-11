package fer.shop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fer.shop.dto.CategoryDto;
import fer.shop.dto.ProductDto;
import fer.shop.entity.Category;
import fer.shop.entity.Product;
import fer.shop.manager.CategoryManager;
import fer.shop.manager.ProductManager;

@RestController
public class ProductBrowseController {
	@Autowired
	private ProductManager productManager;
	public void setUserManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	@Autowired
	private CategoryManager categoryManager;
	public void setUserManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	@RequestMapping(value = "/api/products/categories", produces = "application/json", method = GET)
	public List<CategoryDto> getCategories() {
		List<Category> categories = categoryManager.getCategories();
		List<CategoryDto> categoriesResponse = new ArrayList<>();
		if(categories.size()>0){
			categories.forEach(c -> categoriesResponse.add(new CategoryDto(c)));
		}else{
			System.out.println("No categories in database");
		}
		return categoriesResponse;
	}
	
	@RequestMapping(value = "/api/products/categories/create", method = POST, consumes = "application/json")
	public String createCategory(@RequestBody CategoryDto newCat) {

		System.out.println(newCat.getCatName());
		System.out.println(newCat.getCatDescription());

		
		if(newCat.getCatName().length()<1 || newCat.getCatDescription().length()<1){
			return "Please complete all fields";
		}
		// TODO This should be a proper response object with better formatted
		// output / information similar to the loan request in the future
		String response = "Category created successfully!";
		categoryManager.createCategory(newCat.getCatName(), newCat.getCatDescription());
		return response;
	}
	
	@RequestMapping(value = "/api/products/categories/delete", method = POST)
	public String delete(@RequestParam("catid") Long id) {
		System.out.println("Deleting "+id);
		Category cat = categoryManager.getCategory(id);
		if(cat != null){
			System.out.println("Category retrieved");
			categoryManager.deleteCategory(cat);
		}else{
			System.out.println("Category not found");
		}
		return "Category Deleted";
	}
	
	@RequestMapping(value = "/api/products/categories/{catId}", produces = "application/json", method = GET)
	public Set<ProductDto> getCatProducts(@PathVariable("catId") Long id) {
		System.out.println("Getting category products");
		Set<Product> products = new HashSet<>();
		products = categoryManager.getCategory(id).getProducts();
		Set<ProductDto> ret = new HashSet<>();
		ret = products.stream().map(p -> new ProductDto(p)).collect(Collectors.toSet());
		return ret;
	}
	
	@RequestMapping(value = "/api/categories/{catId}", produces = "application/json", method = GET)
	public CategoryDto getCategory(@PathVariable("catId") Long id) {
		CategoryDto ret = new CategoryDto(categoryManager.getCategory(id));
		return ret;
	}
	
	@RequestMapping(value = "/api/products/{productId}/categories", produces = "application/json", method = GET)
	public Set<Long> getProductCategories(@PathVariable("productId") Long id) {
		System.out.println("Getting product categories");
		Product product = productManager.getProduct(id);
		Set<Long> ret = new HashSet<>();
		ret = product.getProductCategories().stream().map(c -> c.getCatId()).collect(Collectors.toSet());
		return ret;
	}
	
	@RequestMapping(value = "/api/products/{productId}/categories/add", method = POST, consumes = "application/json")
	public String addProductCategories(@PathVariable("productId") Long id, @RequestBody Set<Long> catIds){
		catIds.stream().forEach(c -> categoryManager.categoryAddProduct(c, id));
		return "Success";
	}
	
	@RequestMapping(value = "/api/products/{productId}/categories/update", method = POST, consumes = "application/json")
	public String updateProductCategories(@PathVariable("productId") Long id, @RequestBody Set<Long> catIds){
		catIds.forEach(c -> System.out.println(c));
		categoryManager.updateProductCategories(id, catIds);
		return "Success";
	}
	
}
