package pe.aqllay.backend.services;

import java.util.List;

import org.bson.types.ObjectId;


import pe.aqllay.backend.documents.Customer;


public interface CustomerService {

    public Customer get(ObjectId id);
    
    public List<Customer> findAll();
    
    public Customer save(Customer customer);
    
    public Customer update(Customer customer);
    
    public void delete(ObjectId id);
    
}
