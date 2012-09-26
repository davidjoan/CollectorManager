package pe.aqllay.backend.services;

import java.util.List;

import org.bson.types.ObjectId;


import pe.aqllay.backend.documents.Account;

public interface AccountService {

    public Account get(ObjectId id);
    
    public List<Account> findAll();
    
    public Account save(Account account);
    
    public Account update(Account account);
    
    public void delete(ObjectId id);
    
}
