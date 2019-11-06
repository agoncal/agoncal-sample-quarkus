package org.agoncal.sample.quarkus.without;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

public class AddressServiceTest {

    @ClassRule
    public static JtaEnvironment jtaEnvironment = new JtaEnvironment();

    @Rule
    public WeldInitiator weld = WeldInitiator.from(new Weld())
        .activate(RequestScoped.class)
        .inject(this)
        .build();

    @Inject
    private AddressService addressService;

    @Test
    public void shouldCreateAnAddress() {
        Address address = new Address().street1("233 Spring Street").city("New York").zipcode("12345");
        addressService.save(address);
        Assert.assertNotNull("Id should not be null", address.getId());
        address = addressService.findById(address.getId());
        Assert.assertEquals("New York", address.getCity());
    }
}
