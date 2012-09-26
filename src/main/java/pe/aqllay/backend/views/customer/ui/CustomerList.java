package pe.aqllay.backend.views.customer.ui;

import pe.aqllay.backend.documents.Customer;
import pe.aqllay.backend.views.customer.CustomerWindow;
import pe.aqllay.backend.views.customer.data.CustomerContainer;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class CustomerList extends Table {
    public CustomerList(CustomerWindow app) {
        setSizeFull();
        setContainerDataSource(app.getDataSource());

        setVisibleColumns(CustomerContainer.NATURAL_COL_ORDER);
        setColumnHeaders(CustomerContainer.COL_HEADERS_ENGLISH);

        setColumnCollapsingAllowed(true);
        setColumnReorderingAllowed(true);

        /*
         * Make table selectable, react immediatedly to user events, and pass
         * events to the controller (our main application)
         */
        setSelectable(true);
        setImmediate(true);
        addListener((ValueChangeListener) app);
        /* We don't want to allow users to de-select a row */
        setNullSelectionAllowed(false);

        // customize email column to have mailto: links using column generator
     /*   addGeneratedColumn("email", new ColumnGenerator() {
            public Component generateCell(Table source, Object itemId,
                    Object columnId) {
                Customer p = (Customer) itemId;
                Link l = new Link();
                l.setResource(new ExternalResource("mailto:" + p.getEmail()));
                l.setCaption(p.getEmail());
                return l;
            }
        });*/
    }

}