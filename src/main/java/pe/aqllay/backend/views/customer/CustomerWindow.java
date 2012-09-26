package pe.aqllay.backend.views.customer;

import pe.aqllay.backend.services.CustomerService;

import pe.aqllay.backend.utils.SpringContextHelper;
import pe.aqllay.backend.views.CollectorManagerApplication;
import pe.aqllay.backend.views.customer.data.CustomerContainer;
import pe.aqllay.backend.views.customer.data.SearchFilter;
import pe.aqllay.backend.views.customer.ui.CustomerForm;
import pe.aqllay.backend.views.customer.ui.CustomerList;
import pe.aqllay.backend.views.customer.ui.HelpWindow;
import pe.aqllay.backend.views.customer.ui.ListView;
import pe.aqllay.backend.views.customer.ui.NavigationTree;
import pe.aqllay.backend.views.customer.ui.SearchView;
import pe.aqllay.backend.views.customer.ui.SharingOptions;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class CustomerWindow extends Window implements ClickListener, ValueChangeListener, ItemClickListener {

    private NavigationTree tree = new NavigationTree(this);

    private Button newContact = new Button("Add contact");
    private Button search = new Button("Buscar");
    private Button share = new Button("Compartir");
    private Button help = new Button("Ayuda");
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();

    // Lazyly created ui references
    private ListView listView = null;
    private SearchView searchView = null;
    private CustomerList personList = null;
    private CustomerForm personForm = null;
    private HelpWindow helpWindow = null;
    private SharingOptions sharingOptions = null;

    //private CustomerContainer dataSource = CustomerContainer.createWithTestData();
    private CustomerContainer dataSource = null;
    
    
    private CustomerService customerService = null;   
    
    
    private Application application = null;


    public CustomerWindow(Application app) {
	super("customer-manager");
	application = app;
	this.setCaption("Administrador de Clientes");
	this.setHeight("95%");
	this.setWidth("95%");
	try {
	    SpringContextHelper helper = new SpringContextHelper(application);
	    customerService = (CustomerService) helper.getBean("customerService");
	    dataSource = new CustomerContainer(customerService).getCustomers();
	    
	    
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	}
	
	buildMainLayout();
        setMainComponent(getListView());
    }


    private void buildMainLayout() {


        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        layout.addComponent(createToolbar());
        layout.addComponent(horizontalSplit);
        layout.setExpandRatio(horizontalSplit, 1);

        horizontalSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        horizontalSplit.setFirstComponent(tree);

        setContent(layout);
    }

    private HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();
        lo.addComponent(newContact);
        lo.addComponent(search);
     //   lo.addComponent(share);
     //   lo.addComponent(help);

        search.addListener((ClickListener) this);
        share.addListener((ClickListener) this);
      //  help.addListener((ClickListener) this);
        newContact.addListener((ClickListener) this);

        search.setIcon(new ThemeResource("icons/32/folder-add.png"));
        share.setIcon(new ThemeResource("icons/32/users.png"));
    //    help.setIcon(new ThemeResource("icons/32/help.png"));
        newContact.setIcon(new ThemeResource("icons/32/document-add.png"));

        lo.setMargin(true);
        lo.setSpacing(true);

        lo.setStyleName("toolbar");

        lo.setWidth("100%");

        Embedded em = new Embedded("", new ThemeResource("images/logo.jpg"));
        lo.addComponent(em);
        lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
        lo.setExpandRatio(em, 1);

        return lo;
    }

    private void setMainComponent(Component c) {
        horizontalSplit.setSecondComponent(c);
    }

    /*
     * View getters exist so we can lazily generate the views, resulting in
     * faster application startup time.
     */
    private ListView getListView() {
        if (listView == null) {
            personList = new CustomerList(this);
            personForm = new CustomerForm((CollectorManagerApplication)application, this);
            listView = new ListView(personList, personForm);
        }
        return listView;
    }

    private SearchView getSearchView() {
        if (searchView == null) {
            searchView = new SearchView(this);
        }
        return searchView;
    }

    private HelpWindow getHelpWindow() {
        if (helpWindow == null) {
            helpWindow = new HelpWindow();
        }
        return helpWindow;
    }

    private SharingOptions getSharingOptions() {
        if (sharingOptions == null) {
            sharingOptions = new SharingOptions();
        }
        return sharingOptions;
    }

    public CustomerContainer getDataSource() {
        return dataSource;
    }

    public void buttonClick(ClickEvent event) {
        final Button source = event.getButton();

        if (source == search) {
            showSearchView();
        } else if (source == help) {
            showHelpWindow();
        } else if (source == share) {
            showShareWindow();
        } else if (source == newContact) {
            addNewContanct();
        }
    }

    private void showHelpWindow() {
	this.application.addWindow(getHelpWindow());
    }

    private void showShareWindow() {
	this.application.addWindow(getSharingOptions());
    }

    private void showListView() {
        setMainComponent(getListView());
    }

    private void showSearchView() {
        setMainComponent(getSearchView());
    }

    public void valueChange(ValueChangeEvent event) {
        Property property = event.getProperty();
        if (property == personList) {
            Item item = personList.getItem(personList.getValue());
            if (item != personForm.getItemDataSource()) {
                personForm.setItemDataSource(item);
            }
        }
    }

    public void itemClick(ItemClickEvent event) {
        if (event.getSource() == tree) {
            Object itemId = event.getItemId();
            if (itemId != null) {
                if (NavigationTree.SHOW_ALL.equals(itemId)) {
                    // clear previous filters
                    getDataSource().removeAllContainerFilters();
                    showListView();
                } else if (NavigationTree.SEARCH.equals(itemId)) {
                    showSearchView();
                } else if (itemId instanceof SearchFilter) {
                    search((SearchFilter) itemId);
                }
            }
        }
    }

    private void addNewContanct() {
        showListView();
        personForm.addCustomer();
    }

    public void search(SearchFilter searchFilter) {
        // clear previous filters
        getDataSource().removeAllContainerFilters();
        // filter contacts with given filter
        getDataSource().addContainerFilter(searchFilter.getPropertyId(),
                searchFilter.getTerm(), true, false);
        showListView();

        showNotification(
                "Searched for " + searchFilter.getPropertyId() + "=*"
                        + searchFilter.getTerm() + "*, found "
                        + getDataSource().size() + " item(s).",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void saveSearch(SearchFilter searchFilter) {
        tree.addItem(searchFilter);
        tree.setParent(searchFilter, NavigationTree.SEARCH);
        // mark the saved search as a leaf (cannot have children)
        tree.setChildrenAllowed(searchFilter, false);
        // make sure "Search" is expanded
        tree.expandItem(NavigationTree.SEARCH);
        // select the saved search
        tree.setValue(searchFilter);
    }

}
