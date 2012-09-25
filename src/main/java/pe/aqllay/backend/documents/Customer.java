package pe.aqllay.backend.documents;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer extends Base {
    // dni
    @Indexed
    private String identityDocument;

    // nombre del cliente
    @Indexed
    private String name;

    // id del gestor
    @DBRef
    private Manager manager;

    public String getIdentityDocument() {
	return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
	this.identityDocument = identityDocument;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Manager getManager() {
	return manager;
    }

    public void setManager(Manager manager) {
	this.manager = manager;
    }
}
