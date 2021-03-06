package pe.aqllay.backend.documents;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Manager extends Base {

	@Indexed
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	    @Override
	    public String toString() {
		return name;

	    } 

}
