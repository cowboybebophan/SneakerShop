package jdbc;

import com.ascending.jdbc.ProductDao;
import com.ascending.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoTest {
    private ProductDao productDao;

    @Before
    public void init() { productDao = new ProductDao(); }

    @Test
    public void getProductsTest(){
        List<Product> products = productDao.getProducts();
        int expectationNumOfProducts = 4;

        for (Product product : products){
            System.out.println(product);
        }

        Assert.assertEquals(expectationNumOfProducts, products.size());
    }

}
