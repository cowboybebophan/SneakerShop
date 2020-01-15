package service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerServiceTest.class,
        OrderServiceTest.class,
        ProductServiceTest.class,
        FileServiceTest.class
})
public class TestAll {
}
