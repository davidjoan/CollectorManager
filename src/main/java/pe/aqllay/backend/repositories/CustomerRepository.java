package pe.aqllay.backend.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pe.aqllay.backend.documents.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

}



