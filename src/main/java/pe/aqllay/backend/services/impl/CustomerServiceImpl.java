package pe.aqllay.backend.services.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.repositories.CustomerRepository;
import pe.aqllay.backend.services.CustomerService;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer get(ObjectId id) {
	return customerRepository.findOne(id);

    }

    public List<Customer> findAll() {
	return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
	customer.setId(ObjectId.get());
	customer.setCreatedAt(new Date());

	return customerRepository.save(customer);

    }

    public Customer update(Customer customer) {
	Customer response = null;

	if (customerRepository.exists(customer.getId())) {
	    customer.setUpdatedAt(new Date());
	    response = customerRepository.save(customer);
	} else {
	    response = new Customer();
	}

	return response;
    }

    public void delete(ObjectId id) {
	customerRepository.delete(id);

    }

}