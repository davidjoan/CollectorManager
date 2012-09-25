package pe.aqllay.backend.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    public Customer findById(ObjectId id)
    {
	return customerRepository.findOne(id);
    }
    
    public List<Customer> findALl()
    {
	return customerRepository.findAll();
    }
    
    public Customer insert(Customer customer)
    {
	customer.setId(ObjectId.get());
	customer.setCreatedAt(new Date());
	
	return customerRepository.save(customer);
    }
    
    public Customer update(Customer customer)
    {
	customer.setUpdatedAt(new Date());
	
	return customerRepository.save(customer);
    }    
    
    public void delete(ObjectId id)
    {
	customerRepository.delete(id);
    }
    
}
