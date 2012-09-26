package pe.aqllay.backend.views.customer.ui;

import java.util.Arrays;
import java.util.List;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.documents.Manager;
import pe.aqllay.backend.services.CustomerService;
import pe.aqllay.backend.services.ManagerService;
import pe.aqllay.backend.utils.SpringContextHelper;
import pe.aqllay.backend.views.CollectorManagerApplication;
import pe.aqllay.backend.views.customer.CustomerWindow;
import pe.aqllay.backend.views.customer.data.CustomerContainer;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;


import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class CustomerForm extends Form implements ClickListener {

    private Button save = new Button("Guardar", (ClickListener) this);
    private Button cancel = new Button("Cancelar", (ClickListener) this);
    private Button edit = new Button("Editar", (ClickListener) this);
    private final ComboBox managers = new ComboBox("Gestor");

    private CollectorManagerApplication app;
    private CustomerWindow customerWindow= null;
    private boolean newContactMode = false;
    private Customer newPerson = null;

    public CustomerForm(CollectorManagerApplication app, CustomerWindow customerWindow) {
        this.app = app;
        this.customerWindow = customerWindow;
        
        

        /*
         * Enable buffering so that commit() must be called for the form before
         * input is written to the data. (Form input is not written immediately
         * through to the underlying object.)
         */
        setWriteThrough(false);

        HorizontalLayout footer = new HorizontalLayout();
        footer.setSpacing(true);
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.setVisible(false);

        setFooter(footer);
        
	SpringContextHelper helper = new SpringContextHelper(app);
	ManagerService	managerService = (ManagerService) helper.getBean("managerService");


       //Allow the user to enter new cities 
	managers.setNewItemsAllowed(true);
        // We do not want to use null values 
	managers.setNullSelectionAllowed(false);
        // Add an empty city used for selecting no city 
	managers.addItem("");
	
	List<Manager> managersList = managerService.findAll();
	
	for (Manager manager : managersList) {
	    //String city = manager.getName();
            managers.addItem(manager);
            
	}
	
	
	/*
         * Field factory for overriding how the component for city selection is
         * created
         */
        setFormFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId,
                    Component uiContext) {
                if (propertyId.equals("manager")) {
                    managers.setWidth("200px");
                    return managers;
                }

                Field field = super.createField(item, propertyId, uiContext);
                

                field.setWidth("200px");
                return field;
            }
        });	

   

    }

    public void buttonClick(ClickEvent event) {
        Button source = event.getButton();

        if (source == save) {
            /* If the given input is not valid there is no point in continuing */
            if (!isValid()) {
        	
        	
                return;
            }

            commit();
            if (newContactMode) {
                /* We need to add the new person to the container */
                Item addedItem = customerWindow.getDataSource().addItem(newPerson);
                
                
        	    SpringContextHelper helper = new SpringContextHelper(app);
        	    	CustomerService	customerService = (CustomerService) helper.getBean("customerService");
        	    	customerService.save(newPerson);
                

                /*
                 * We must update the form to use the Item from our datasource
                 * as we are now in edit mode (no longer in add mode)
                 */
                setItemDataSource(addedItem);

                newContactMode = false;
            }
            setReadOnly(true);
        } else if (source == cancel) {
            if (newContactMode) {
                newContactMode = false;
                /* Clear the form and make it invisible */
                setItemDataSource(null);
            } else {
                discard();
            }
            setReadOnly(true);
        } else if (source == edit) {
            setReadOnly(false);

        }
    }

    @Override
    public void setItemDataSource(Item newDataSource) {
        newContactMode = false;

        if (newDataSource != null) {
            List<Object> orderedProperties = Arrays
                    .asList(CustomerContainer.NATURAL_COL_ORDER);
            super.setItemDataSource(newDataSource, orderedProperties);

            setReadOnly(true);
            getFooter().setVisible(true);
        } else {
            super.setItemDataSource(null);
            getFooter().setVisible(false);
        }
        
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        save.setVisible(!readOnly);
        cancel.setVisible(!readOnly);
        edit.setVisible(readOnly);
    }

    public void addCustomer() {
        // Create a temporary item for the form
        newPerson = new Customer();
        setItemDataSource(new BeanItem<Customer>(newPerson));
        newContactMode = true;
        setReadOnly(false);
    }

}