package ca.ulaval.glo2003;

import ca.ulaval.glo2003.customer.infra.mongo.CustomerMongo;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import dev.morphia.query.filters.Filters;
import java.util.Optional;

public class MorphiaTest {
    public static void main (String[] args){
        var mongoUrl = "mongodb://root:example@localhost:27017";
        final Datastore datastore = Morphia.createDatastore(MongoClients.create(mongoUrl), "restalo");

        var customer = new CustomerMongo();
        datastore.save(customer);

        //obtenir les documents
        var query = datastore.find(CustomerMongo.class).filter(
                Filters.eq("_id", "abc")
        );
        var foundCustomer = Optional.ofNullable(query.iterator().tryNext());
        System.out.println(foundCustomer);
    }
}
