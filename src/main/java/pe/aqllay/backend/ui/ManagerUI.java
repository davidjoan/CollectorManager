package pe.aqllay.backend.ui;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.aqllay.backend.documents.Manager;
import pe.aqllay.backend.services.ManagerService;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ManagerUI extends Application {
    
    private static String[] fields = { "Nombre","Fecha Creación", "Fecha Actualización" };
    
    private static String[] visibleCols = new String[] { "Nombre",
        "Fecha Creación", "Fecha Actualización" };
    
    private Table managerList = new Table();
    
    private Form managerEditor = new Form();
    
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    private Button contactRemovalButton;
    private IndexedContainer addressBookData = loadData();
    
    
    @Autowired
    private ManagerService managerService;


    @Override
    public void init() {
	initLayout();
	initContactAddRemoveButtons();
	initAddressList();
	initFilteringControls();
	
    }
    
    private void initLayout() {
    	
    	HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
    	setMainWindow(new Window("Administrador de Gestores", splitPanel));
    	VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        left.addComponent(managerList);
        managerList.setSizeFull();
        
        left.setExpandRatio(managerList, 1);
        splitPanel.addComponent(left);
        splitPanel.addComponent(managerEditor);
        
        managerEditor.setCaption("Manager details editor");
        managerEditor.setSizeFull();
        managerEditor.getLayout().setMargin(true);
        managerEditor.setImmediate(true);
        bottomLeftCorner.setWidth("100%");
        left.addComponent(bottomLeftCorner);

    }
    
    private void initContactAddRemoveButtons() {
        // New item button
        bottomLeftCorner.addComponent(new Button("+",
                new Button.ClickListener() {
                    public void buttonClick(ClickEvent event) {
                        // Add new contact "John Doe" as the first item
                        Object id = ((IndexedContainer) managerList
                              .getContainerDataSource()).addItemAt(0);
                        managerList.getItem(id).getItemProperty("Nombre")
                              .setValue("demo");
                        managerList.getItem(id).getItemProperty("Fecha Creación")
                              .setValue(new Date());
                        managerList.getItem(id).getItemProperty("Fecha Actualización")
                        .setValue(new Date());

                        // Select the newly added item and scroll to the item
                        managerList.setValue(id);
                        managerList.setCurrentPageFirstItemId(id);
                    }
                }));

        // Remove item button
        contactRemovalButton = new Button("-", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                managerList.removeItem(managerList.getValue());
                managerList.select(null);
            }
        });
        contactRemovalButton.setVisible(false);
        bottomLeftCorner.addComponent(contactRemovalButton);
    }
    
    private void initAddressList() {
        managerList.setContainerDataSource(addressBookData);
        managerList.setVisibleColumns(visibleCols);
        managerList.setSelectable(true);
        managerList.setImmediate(true);
        managerList.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Object id = managerList.getValue();
                managerEditor.setItemDataSource(id == null ? null : managerList
                        .getItem(id));
                contactRemovalButton.setVisible(id != null);
            }
        });
    }
    
    private void initFilteringControls() {
        for (final String pn : visibleCols) {
            final TextField sf = new TextField();
            bottomLeftCorner.addComponent(sf);
            sf.setWidth("100%");
            sf.setInputPrompt(pn);
            sf.setImmediate(true);
            bottomLeftCorner.setExpandRatio(sf, 1);
            sf.addListener(new Property.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    addressBookData.removeContainerFilters(pn);
                    if (sf.toString().length() > 0 && !pn.equals(sf.toString())) {
                        addressBookData.addContainerFilter(pn, sf.toString(),
                                true, false);
                    }
                    getMainWindow().showNotification(
                            "" + addressBookData.size() + " matches found");
                }
            });
        }
    }
    
    private IndexedContainer loadData() {
	
        IndexedContainer ic = new IndexedContainer();
        
        List<Manager> managers = managerService.findAll();
        
        for (Manager manager : managers) {
            Object id = ic.addItem();
	    ic.getContainerProperty(id, "Nombre").setValue(manager.getName());
	    ic.getContainerProperty(id, "Fecha Creación").setValue(manager.getCreatedAt());
	    ic.getContainerProperty(id, "Fecha Actualización").setValue(manager.getUpdatedAt());
	    
	}


        return ic;
    }
        

}
