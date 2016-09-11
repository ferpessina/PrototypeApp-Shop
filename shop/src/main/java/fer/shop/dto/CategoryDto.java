package fer.shop.dto;

import fer.shop.entity.Category;

public class CategoryDto {
	private Long catId;
	private String catName;
	private String catDescription;
	
	public CategoryDto(){};
	public CategoryDto(Category cat){
		this.catId = cat.getCatId();
		this.catName = cat.getCatName();
		this.catDescription = cat.getCatDescription();
	}
	public CategoryDto(String catName, String catDescription){
		this.catName = catName;
		this.catDescription = catDescription;
	}
	
	public Long getCatId() {
		return catId;
	}
	public void setCatId(Long catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getCatDescription() {
		return catDescription;
	}
	public void setCatDescription(String catDescription) {
		this.catDescription = catDescription;
	}

}
