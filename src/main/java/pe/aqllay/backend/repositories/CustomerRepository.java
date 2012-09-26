package pe.aqllay.backend.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import pe.aqllay.backend.documents.Customer;

@Transactional
public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {

}
