package pe.aqllay.backend.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pe.aqllay.backend.documents.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, ObjectId> {

}



