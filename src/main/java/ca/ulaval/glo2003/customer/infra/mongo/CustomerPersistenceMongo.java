package ca.ulaval.glo2003.customer.infra.mongo;

import ca.ulaval.glo2003.customer.logic.Customer;
import ca.ulaval.glo2003.customer.logic.CustomerPersistence;
import dev.morphia.Datastore;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class CustomerPersistenceMongo implements CustomerPersistence {

    private final Datastore datastore;

    public CustomerPersistenceMongo(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public List<Customer> listAll() {
        return datastore.find(CustomerMongo.class)
                .stream()
                .map(customerMongo -> new Customer(
                        customerMongo.id,
                        customerMongo.name,
                        LocalDate.parse(customerMongo.birthDate, DateTimeFormatter.ISO_LOCAL_DATE),
                        new HashMap<>()))
                .toList();
    }

    @Override
    public void save(Customer customer) {
        datastore.save(
                new CustomerMongo(
                        customer.id(),
                        customer.name(),
                        customer.birthDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                )
        );

    }
}
