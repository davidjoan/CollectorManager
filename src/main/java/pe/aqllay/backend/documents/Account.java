package pe.aqllay.backend.documents;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Account extends Base {

	@Indexed
	private String number;
	
	//id del Gestor
	@DBRef
	private Manager manager;
	
	//id del cliente
	@DBRef
	private Customer customer;
	
	//description del producto
	private String description;
	
	//total deuda
	private String totalDebt;
	
	//dias de mora
	private Integer daysOfDelay;
	
	//deuda vencida
	private String overdueDebt;
	
	//deuda capital
	private String capitalDebt;


	public String getNumber() {
	    return number;
	}

	public void setNumber(String number) {
	    this.number = number;
	}

	public Manager getManager() {
	    return manager;
	}

	public void setManager(Manager manager) {
	    this.manager = manager;
	}

	public Customer getCustomer() {
	    return customer;
	}

	public void setCustomer(Customer customer) {
	    this.customer = customer;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getTotalDebt() {
	    return totalDebt;
	}

	public void setTotalDebt(String totalDebt) {
	    this.totalDebt = totalDebt;
	}

	public Integer getDaysOfDelay() {
	    return daysOfDelay;
	}

	public void setDaysOfDelay(Integer daysOfDelay) {
	    this.daysOfDelay = daysOfDelay;
	}

	public String getOverdueDebt() {
	    return overdueDebt;
	}

	public void setOverdueDebt(String overdueDebt) {
	    this.overdueDebt = overdueDebt;
	}

	public String getCapitalDebt() {
	    return capitalDebt;
	}

	public void setCapitalDebt(String capitalDebt) {
	    this.capitalDebt = capitalDebt;
	}
}
