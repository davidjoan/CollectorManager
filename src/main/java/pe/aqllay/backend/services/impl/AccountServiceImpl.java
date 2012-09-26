package pe.aqllay.backend.services.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aqllay.backend.documents.Account;
import pe.aqllay.backend.repositories.AccountRepository;
import pe.aqllay.backend.services.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account get(ObjectId id) {
	return accountRepository.findOne(id);

    }

    public List<Account> findAll() {
	return accountRepository.findAll();
    }

    public Account save(Account manager) {
	manager.setId(ObjectId.get());
	manager.setCreatedAt(new Date());

	return accountRepository.save(manager);

    }

    public Account update(Account account) {
	Account response = null;

	if (accountRepository.exists(account.getId())) {
	    account.setUpdatedAt(new Date());
	    response = accountRepository.save(account);
	} else {
	    response = new Account();
	}

	return response;
    }

    public void delete(ObjectId id) {
	accountRepository.delete(id);

    }

}