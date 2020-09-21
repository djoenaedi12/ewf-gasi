package gasi.ewf.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gasi.ewf.entity.Client;
import gasi.ewf.repository.ClientRepository;

@Service
@Transactional(rollbackOn = {Exception.class})
public class ClientServiceImp implements ClientService{

	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	public Optional<Client> findById(Long id) {
		return clientRepository.findById(id);
	}

	@Override
	public Optional<Client> findByCode(String code) {
		return clientRepository.findByCode(code);
	}

	@Override
	public void delete(Client client) {
		clientRepository.delete(client);
	}

}
