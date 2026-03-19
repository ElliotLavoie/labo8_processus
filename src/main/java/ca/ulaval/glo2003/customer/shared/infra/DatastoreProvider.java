package ca.ulaval.glo2003.customer.shared.infra;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

public class DatastoreProvider {

    public Datastore provide(){
        var mongoUrl = "mongodb://root:example@localhost:27017";
        return Morphia.createDatastore(MongoClients.create(mongoUrl), "restalo");
    }
}
