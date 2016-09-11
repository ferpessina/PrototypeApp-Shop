package fer.shop.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.shop.dao.ProductDao;
import fer.shop.dto.ProductDto;
import fer.shop.entity.Product;
import fer.shop.entity.User;

@Service
public class ProductManager {
	@Autowired
	private ProductDao productDao;
	public void setUserDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	@Autowired
	private UserManager userManager;
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	@Autowired
	private CategoryManager categoryManager;
	public void setUserManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	public void updateProduct(ProductDto productDto){
		Product product = productDao.findOne(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductState(productDto.getProductState());
		product.setProductImage(productDto.getProductImage());
		productDao.save(product);
	}
	
	public void cancelReservation(Product product){
		product = productDao.findOne(product.getProductId());
		product.setReservation(null);
		productDao.save(product);
	}
	
	public Set<ProductDto> getByUserId(Long userId){
		User user = null;
		user = userManager.getUserById(userId);
		Set<ProductDto> products = new HashSet<>();
		if(user != null){
			List<Product> _products = new ArrayList<Product>(user.getUserProducts());
			for(int i=0;i<_products.size();i++){
				products.add(new ProductDto(_products.get(i)));
			}
		}
		return products;
	}
	
	public void delete(Product product){
		product = productDao.findOne(product.getProductId());
		Long id = product.getProductId();
		product.getProductCategories().stream().forEach(c -> categoryManager.categoryRemoveProduct(c.getCatId(), id));
		productDao.delete(product);
	}
	
	public Product addProduct(ProductDto productDto){
		User owner = userManager.getUserById(productDto.getOwnerId());
		Product product = new Product(productDto.getProductName(), productDto.getProductDescription(), productDto.getProductPrice(), productDto.getProductState(), owner, productDto.getProductImage());
		product.setListDate(new Date());
		productDao.save(product);
		return product;
	}
	
	public Product getProduct(Long id){
		return productDao.findOne(id);
	}
}
