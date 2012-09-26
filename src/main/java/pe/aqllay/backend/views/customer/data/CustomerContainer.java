package pe.aqllay.backend.views.customer.data;

import java.io.Serializable;
import java.util.List;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.services.CustomerService;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CustomerContainer extends BeanItemContainer<Customer> implements Serializable {

    /**
     * Natural property order for Person bean. Used in tables and forms.
     */
    public static final Object[] NATURAL_COL_ORDER = new Object[] {"identityDocument", "name", "manager"};

    /**
     * "Human readable" captions for properties in same order as in NATURAL_COL_ORDER.
     */
    public static final String[] COL_HEADERS_ENGLISH = new String[] {"DNI", "Nombre", "Gestor"};
    
    private CustomerService customerService;

    public CustomerContainer(CustomerService customerService) throws InstantiationException, IllegalAccessException {
        super(Customer.class);
        this.customerService = customerService;
    }
    
    public CustomerContainer getCustomers()
    {
	
	List<Customer> customers = customerService.findAll();
	
	for (Customer customer : customers) {
	 addItem(customer);   
	}
	
	return this;
	
    }

}
