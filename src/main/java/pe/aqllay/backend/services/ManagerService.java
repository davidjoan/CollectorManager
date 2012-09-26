package pe.aqllay.backend.services;

import java.util.List;

import org.bson.types.ObjectId;

import pe.aqllay.backend.documents.Manager;


public interface ManagerService {
    
    public Manager get(ObjectId id);
    
    public List<Manager> findAll();
    
    public Manager save(Manager manager);
    
    public Manager update(Manager manager);
    
    public void delete(ObjectId id);
    
}
