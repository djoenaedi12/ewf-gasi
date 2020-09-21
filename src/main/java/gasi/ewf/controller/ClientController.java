package gasi.ewf.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gasi.ewf.entity.Client;
import gasi.ewf.request.ClientRequest;
import gasi.ewf.service.ClientService;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(){
		List<Client> list = clientService.findAll();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/client/{code}", method = RequestMethod.GET)
	public ResponseEntity<?> getByCode(@PathVariable String code) throws Exception{
		Client client = clientService.findByCode(code)
				.orElseThrow(() ->
                new Exception("Client with code " + code + " not found")
		);

		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public ResponseEntity<?> add(@Valid @RequestBody ClientRequest request) throws NumberFormatException, Exception{
		Client client = new Client();
		client.setCode(request.getCode());
		client.setName(request.getName());
		client.setUrl(request.getUrl());
		client.setImage(request.getImage());
		client.setSecret(request.getSecret());
		clientService.save(client);
		
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/client/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String code) throws Exception{
		Client client = clientService.findByCode(code)
				.orElseThrow(() ->
                new Exception("Client with code " + code + " not found")
		);
		clientService.delete(client);
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/client/{code}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String code, @Valid @RequestBody ClientRequest request) throws Exception{
		Client client = clientService.findByCode(code)
				.orElseThrow(() ->
				new Exception("Client with code " + code + "not found")
		);
		
		client.setCode(request.getCode());
		client.setName(request.getName());
		client.setUrl(request.getUrl());
		client.setImage(request.getImage());
		client.setSecret(request.getSecret());
		clientService.save(client);
		
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
