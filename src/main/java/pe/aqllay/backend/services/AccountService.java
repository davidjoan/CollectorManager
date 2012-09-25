package pe.aqllay.backend.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.aqllay.backend.documents.Account;
import pe.aqllay.backend.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    
    public Account findById(ObjectId id)
    {
	return accountRepository.findOne(id);
    }
    
    public List<Account> findALl()
    {
	return accountRepository.findAll();
    }
    
    public Account insert(Account account)
    {
	account.setId(ObjectId.get());
	account.setCreatedAt(new Date());
	
	return accountRepository.save(account);
    }
    
    public Account update(Account account)
    {
	account.setUpdatedAt(new Date());
	
	return accountRepository.save(account);
    }    
    
    public void delete(ObjectId id)
    {
	accountRepository.delete(id);
    }
    
}
