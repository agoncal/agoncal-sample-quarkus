package org.agoncal.sample.quarkus.with;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class AddressServiceTest {

    @Inject
    private AddressService addressService;

    @Test
    public void shouldCreateAnAddress() {
        Address address = new Address().street1("233 Spring Street").city("New York").zipcode("12345");
        addressService.save(address);
        assertNotNull(address.getId(), "Id should not be null");
        address = addressService.findById(address.getId());
        assertEquals("New York", address.getCity());
    }

    @Test
    public void shoudInjectAddressService() {
        assertNotNull(addressService);
    }
}
