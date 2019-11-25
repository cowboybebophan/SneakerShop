package jdbc;

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
