package pe.aqllay.backend.views;

import pe.aqllay.backend.views.customer.CustomerWindow;

import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

/**
 * The Application's "main" class
 * 
 * @param <E>
 */
@SuppressWarnings("serial")
public class CollectorManagerApplication extends Application {

    private Button openProdManager = new Button("Administrador Clientes");



    @Override
    public void init() {
	setTheme("contacts");
	initLayout();
    }

    private void initLayout() {
	setLayout();
    }

    private void setLayout() {

	// Our main layout is a horizontal layout
	HorizontalLayout main = new HorizontalLayout();
	main.setMargin(true);
	main.setSpacing(true);

	// vertically divide the right area
	VerticalLayout left = new VerticalLayout();
	left.setSpacing(true);
	main.addComponent(left);

	left.addComponent(openProdManager);
	openProdManager.addListener(new Button.ClickListener() {
	    public void buttonClick(ClickEvent event) {
		CustomerWindow productManagerWin = new CustomerWindow(CollectorManagerApplication.this);
		productManagerWin.addListener(new Window.CloseListener() {
		    public void windowClose(CloseEvent e) {
			openProdManager.setEnabled(true);
		    }
		});

		CollectorManagerApplication.this.getMainWindow().addWindow(productManagerWin);
		openProdManager.setEnabled(false);
	    }
	});

	
	setMainWindow(new Window("Sistema de Cobranzas", main));

    }
}