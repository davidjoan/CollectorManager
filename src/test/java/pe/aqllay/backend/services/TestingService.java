package pe.aqllay.backend.services;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.documents.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:collector-manager-spring.xml" })
public class TestingService {
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private CustomerService customerService;    
    
	@Before
	public void init()
	{
		List<Manager> managers = managerService.findAll();
		for (Manager manager : managers){ 
			managerService.delete(manager.getId());
		}
		List<Customer> customers = customerService.findAll();
		for (Customer customer : customers){ 
			customerService.delete(customer.getId());
		}		
	}
	
	@Test
	public void loadManagerData()
	{
	    Manager manager1 = new Manager();
	    manager1.setCreatedAt(new Date());
	    manager1.setActive(true);
	    manager1.setName("Enrique Perez");
	    manager1.setUpdatedAt(new Date());
	    managerService.save(manager1);
	    
	    Manager manager2 = new Manager();
	    manager2.setCreatedAt(new Date());
	    manager2.setActive(true);
	    manager2.setName("Gerardo Tataje");
	    manager2.setUpdatedAt(new Date());
	    managerService.save(manager2);	    
	    
	    Customer customer = new Customer();
	    customer.setCreatedAt(new Date());
	    customer.setActive(true);
	    customer.setName("David Joan Tataje Mendoza");
	    customer.setUpdatedAt(new Date());
	    customer.setIdentityDocument("44796683");
	    customer.setManager(managerService.findAll().get(0));
	    customerService.save(customer);	    
	    
	}
	
}
