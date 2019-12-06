package repository;

import jdbc.CustomerDaoTest;
import jdbc.OrderDaoTest;
import jdbc.ProductDaoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerDaoTest.class,
        OrderDaoTest.class,
        ProductDaoTest.class
})
public class TestAll {

}
