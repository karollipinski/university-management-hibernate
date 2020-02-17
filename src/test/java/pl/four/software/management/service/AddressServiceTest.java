package pl.four.software.management.service;

import org.junit.Assert;
import org.junit.Test;
import pl.four.software.management.model.Address;

public class AddressServiceTest {

    private static AddressService addressService = new AddressService();

    @Test
    public void verifyAddressService() {
        Address address = new Address("Kolorowa", "Warszawa", "Polska");
        int id = addressService.save(address);

        Address address1 = addressService.findById(id);

        address1.setStreet("Lubelska");
        addressService.update(address1);

        address1 = addressService.findById(id);
        Assert.assertEquals("Lubelska", address1.getStreet());

        addressService.delete(address1.getId());
    }

    @Test
    public void testUpdateAddress() {
        Address address = new Address("Kolorowa", "Warszawa", "Polska");
        int id = addressService.save(address);

        Address addressAdded = addressService.findById(id);
        System.out.println("Dodany address");
        System.out.println(addressAdded);

        addressService.updateAddress(id, "Nowa", "Rosja");

        Address addressSaved = addressService.findById(id);
        System.out.println("Zmodyfikowany address");
        System.out.println(addressSaved);

        Assert.assertEquals("Nowa", addressSaved.getStreet());
        Assert.assertEquals("Rosja", addressSaved.getCountry());

        addressService.delete(id);
    }

    @Test
    public void testUpdateAddressHQL() {
        Address address = new Address("Kolorowa", "Warszawa", "Polska");
        int id = addressService.save(address);

        Address addressAdded = addressService.findById(id);
        System.out.println("Dodany addres");
        System.out.println(addressAdded);

        addressService.updateAddressHQL(id, "Nowa", "Rosja");

        Address addressSaved = addressService.findById(id);
        System.out.println("Zmodyfikowany addres");
        System.out.println(addressSaved);

        Assert.assertEquals("Nowa", addressSaved.getStreet());
        Assert.assertEquals("Rosja", addressSaved.getCountry());

        addressService.delete(id);
    }

    @Test
    public void testUpdateAddressSQL() {
        Address address = new Address("Kolorowa", "Warszawa", "Polska");
        int id = addressService.save(address);

        Address addressAdded = addressService.findById(id);
        System.out.println("Dodany addres");
        System.out.println(addressAdded);

        addressService.updateAddressSQL(id, "Nowa", "Rosja");

        Address addressSaved = addressService.findById(id);
        System.out.println("Zmodyfikowany addres");
        System.out.println(addressSaved);

        Assert.assertEquals("Nowa", addressSaved.getStreet());
        Assert.assertEquals("Rosja", addressSaved.getCountry());

        addressService.delete(id);
    }
}
