package repository;

import com.ascending.model.Product;
import com.ascending.repository.ProductDao;
import com.ascending.repository.ProductDaoImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

public class ProductDaoTest {
    private static ProductDao productDao;
    private Product product;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeClass
    public static void init() {
        productDao = new ProductDaoImpl();
    }

    @Test
    public void getProducts() {
        List<Product> products = productDao.getProducts();
        int expectedNumOfProduct = 4;

        for (Product product : products){
            logger.debug(product.toString());
        }

        Assert.assertEquals(expectedNumOfProduct, products.size());
    }

    @Test
    public void getProductByName(){
        String productName = "New Balance 990v2";
        product = productDao.getProductByName(productName);
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

        boolean isSuccess = productDao.save(product);

        Assert.assertEquals(isSuccess, true);

        //Reset the table by deleting the inserted record;
        String condition = "Converse 1970s";
        productDao.delete(condition);
    }

    @Test
    public void deleteProduct(){
        product = new Product();

        product.setName("Converse 1970s");
        product.setDescription("The best causal sneaker in the world!");
        product.setPrice(BigDecimal.valueOf(70));
        product.setStock(80);

        productDao.save(product);
        String productName = "Converse 1970s";
        boolean isSuccess = productDao.delete(productName);

        Assert.assertEquals(isSuccess, true);
    }

    @Test
    public void updateProduct(){
        String productName = "New Balance 990v2";
        product = productDao.getProductByName(productName);

        logger.debug("The product before update: ");
        logger.debug(product.toString());

        //Set new price
        product.setPrice(BigDecimal.valueOf(349.99));
        //Update price
        boolean isSuccess = productDao.update(product);
        Assert.assertEquals(isSuccess, true);

        //Show the updated product
        product = productDao.getProductByName(productName);
        logger.debug("The product after update: ");
        logger.debug(product.toString());
        //Set the price back to what it was
        product.setPrice(BigDecimal.valueOf(300));
        productDao.update(product);

    }
    
}
