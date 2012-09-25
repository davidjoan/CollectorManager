package pe.aqllay.backend.documents;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Base {

    @Id
    private ObjectId id;

    // fecha de carga
    private Date createdAt;

    // fecha de actualizacion
    private Date updatedAt;

    private boolean active = true;

    public ObjectId getId() {
	return id;
    }

    public void setId(ObjectId id) {
	this.id = id;
    }

    public Date getCreatedAt() {
	return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }
    
    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this);

    }    

}
