package pe.aqllay.backend.ui;

import java.awt.Window;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.aqllay.backend.documents.Manager;
import pe.aqllay.backend.services.ManagerService;

import com.vaadin.Application;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class ManagerUI extends Application {
    
    private static String[] fields = { "Nombre" };
    
    private Table managerList = new Table();
    
    private Form managerEditor = new Form();
    
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    private Button contactRemovalButton;
    private IndexedContainer addressBookData = loadData();
    
    
    @Autowired
    private ManagerService managerService;


    @Override
    public void init() {
	// TODO Auto-generated method stub
	
    }
    
    private void initLayout() {
    /*    HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        setMainWindow(new Window("Administrador de Gestores", splitPanel));
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        left.addComponent(contactList);
        managerList.setSizeFull();
        left.setExpandRatio(contactList, 1);
        splitPanel.addComponent(left);
        splitPanel.addComponent(contactEditor);
        managerEditor.setCaption("Contact details editor");
        managerEditor.setSizeFull();
        managerEditor.getLayout().setMargin(true);
        managerEditor.setImmediate(true);
        bottomLeftCorner.setWidth("100%");
        left.addComponent(bottomLeftCorner);*/
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
