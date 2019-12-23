package service;

import com.ascending.init.AppInitializer;
import com.ascending.model.Product;
import com.ascending.repository.ProductDao;
import com.ascending.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class ProductServiceTest {
    @Autowired
    private Logger logger;

    @Autowired
    private ProductDao productDao;

    //@Autowired
    private ProductService productService;
    private Product product;

    @Before
    public void init(){ productService = new ProductService(logger, productDao); }
    
    @Test
    public void getProducts() {
        List<Product> products = productService.getProducts();
        int expectedNumOfProduct = 4;

        for (Product product : products){
            logger.debug(product.toString());
        }

        Assert.assertEquals(expectedNumOfProduct, products.size());
    }

    @Test
    public void getProductByName(){
        String productName = "New Balance 990v2";
        product = productService.getProductByName(productName);
        logger.debug(product.toString());

        Assert.assertEquals(productName, product.getName());
    }

    @Test
    public void saveProduct(){
        product = new Product();

        product.setName("Converse 1970s");
        product.setDescription("The best causal sneaker in the world!");
        product.setPrice(BigDecimal.valueOf(70));
        product.setStock(80);

        boolean isSuccess = productService.save(product);

        Assert.assertEquals(isSuccess, true);

        //Reset the table by deleting the inserted record;
        productService.delete(product.getName());
    }

    @Test
    public void deleteProduct(){
        product = new Product();

        product.setName("Converse 1970s");
        product.setDescription("The best causal sneaker in the world!");
        product.setPrice(BigDecimal.valueOf(70));
        product.setStock(80);

        productService.save(product);
        String productName = "Converse 1970s";
        boolean isSuccess = productService.delete(productName);

        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void updateProduct(){
        String productName = "New Balance 990v2";
        product = productService.getProductByName(productName);

        logger.debug("The product before update: ");
        logger.debug(product.toString());

        //Set new price
        product.setPrice(BigDecimal.valueOf(349.99));
        //Update price
        boolean isSuccess = productService.update(product);
        Assert.assertEquals(isSuccess, true);

        //Show the updated product
        product = productService.getProductByName(productName);
        logger.debug("The product after update: ");
        logger.debug(product.toString());
        //Set the price back to what it was
        product.setPrice(BigDecimal.valueOf(300));
        productService.update(product);
    }
}
