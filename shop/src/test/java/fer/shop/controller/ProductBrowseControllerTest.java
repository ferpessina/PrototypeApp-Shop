package fer.shop.controller;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fer.shop.Application;
import fer.shop.entity.Category;
import fer.shop.manager.CategoryManager;
import fer.shop.manager.ProductManager;
import java.util.*;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ProductBrowseControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    private String userName = "bdussault";

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private ProductManager prodManager;
    private CategoryManager catManager;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    List<Category> categories = new ArrayList<>();
    Category testCategory1;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        catManager = Mockito.mock(CategoryManager.class);
        prodManager = Mockito.mock(ProductManager.class);
        testCategory1 = new Category();
        testCategory1.setCatId((long)2);
        testCategory1.setCatName("testCat");
        testCategory1.setCatDescription("testCatDesc");
        testCategory1.setProducts(null);
        
        Mockito.when(catManager.getCategories()).thenReturn(categories);
        categories.add(testCategory1);
    }
    
	@Test
	public void testGetCategories() throws Exception {
		mockMvc.perform(get("/api/products/categories"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(contentType));
//			.andExpect(jsonPath("$",hasSize(1)));
	}

//	@Test
//	public void testCreateCategory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCatProducts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCategory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetProductCategories() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAddProductCategories() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateProductCategories() {
//		fail("Not yet implemented");
//	}

}
