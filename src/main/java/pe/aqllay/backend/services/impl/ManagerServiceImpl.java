package pe.aqllay.backend.services.impl;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.aqllay.backend.documents.Manager;
import pe.aqllay.backend.repositories.ManagerRepository;
import pe.aqllay.backend.services.ManagerService;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public Manager get(ObjectId id) {
	return managerRepository.findOne(id);

    }

    public List<Manager> findAll() {
	return managerRepository.findAll();
    }

    public Manager save(Manager manager) {
	manager.setId(ObjectId.get());
	manager.setCreatedAt(new Date());

	return managerRepository.save(manager);

    }

    public Manager update(Manager manager) {
	Manager response = null;

	if (managerRepository.exists(manager.getId())) {
	    manager.setUpdatedAt(new Date());
	    response = managerRepository.save(manager);
	} else {
	    response = new Manager();
	}

	return response;
    }

    public void delete(ObjectId id) {
	managerRepository.delete(id);

    }

}