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
    private Product product;

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

    @Test
    public void insertProductTest(){
        product = new Product();

        product.setName("Converse 1970s");
        product.setDescription("The best causal sneaker in the world!");
        product.setPrice(70);
        product.setStock(80);

        int rowsInserted = productDao.insertProduct(product);
        int insertedNumOfRows = 1;

        Assert.assertEquals(insertedNumOfRows, rowsInserted);

        //Reset the table by deleting the inserted record;
        String condition = "name = 'Converse 1970s'";
        productDao.deleteProduct(condition);

    }

    @Test
    public void deleteProductTest(){
        product = new Product();

        product.setName("Converse 1970s");
        product.setDescription("The best causal sneaker in the world!");
        product.setPrice(70);
        product.setStock(80);

        productDao.insertProduct(product);
        String condition = "name = 'Converse 1970s'";
        int rowsDeleted = productDao.deleteProduct(condition);
        int deletedNumOfRows = 1;

        Assert.assertEquals(deletedNumOfRows, rowsDeleted);
    }

    @Test
    public void updateProductTest(){
        String statement = "name = 'New Balance 990v5'";
        String condition = "id = 4 AND price = 300";

        int rowsUpdated = productDao.updateProduct(statement, condition);
        int updatedNumOfRows = 1;

        Assert.assertEquals(updatedNumOfRows, rowsUpdated);

        // Update back
        statement = "name = 'New Balance 990v2'";
        condition = "id = 4 AND price = 300";

        productDao.updateProduct(statement, condition);

    }

}
