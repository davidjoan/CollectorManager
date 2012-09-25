package pe.aqllay.backend.services;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aqllay.backend.documents.Manager;
import pe.aqllay.backend.repositories.ManagerRepository;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    
    public Manager findById(ObjectId id)
    {
	return managerRepository.findOne(id);
    }
    
    public List<Manager> findAll()
    {
	return managerRepository.findAll();
    }
    
    public Manager insert(Manager manager)
    {
	manager.setId(ObjectId.get());
	manager.setCreatedAt(new Date());
	
	return managerRepository.save(manager);
    }
    
    public Manager update(Manager manager)
    {
	manager.setUpdatedAt(new Date());
	
	return managerRepository.save(manager);
    }    
    
    public void delete(ObjectId id)
    {
	managerRepository.delete(id);
    }
    
}
