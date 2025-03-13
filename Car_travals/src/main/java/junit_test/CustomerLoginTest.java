package junit_test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Dao.CustomerDAO;

public class CustomerLoginTest {

    @Before
    public void setUp() {
        // You can insert test data here if needed
    }

    @Test
    public void testValidCustomerLogin() {
        boolean result = CustomerDAO.validateUser("yeya1@gmail.com", "123", "customer");
        assertTrue("Customer login should be successful with correct credentials.", result);
    }

    @Test
    public void testInvalidCustomerLogin() {
        boolean result = CustomerDAO.validateUser("customer@example.com", "wrongpassword", "customer");
        assertFalse("Customer login should fail with incorrect password.", result);
    }

    @Test
    public void testNonExistentCustomerLogin() {
        boolean result = CustomerDAO.validateUser("unknown@example.com", "test123", "customer");
        assertFalse("Login should fail for a non-existent customer.", result);
    }
}

