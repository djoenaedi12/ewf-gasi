package gasi.ewf.service;

import java.util.List;
import java.util.Optional;

import gasi.ewf.entity.Client;


public interface ClientService {

	List<Client> findAll();
	Optional<Client> findById(Long id);
	Optional<Client> findByCode(String code);
	Client save(Client client);
	void delete(Client client);
	
}
