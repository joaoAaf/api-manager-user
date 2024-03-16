package apiManagerUser.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apiManagerUser.domain.Admin;
import apiManagerUser.services.AdminService;

@CrossOrigin
@RestController
@RequestMapping(value="/admins")
public class AdminResources {

	@Autowired
	private AdminService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Admin>> findAll () {
		List<Admin> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Admin> findById (@PathVariable String id) {
		Admin admin = service.findById(id);
		return ResponseEntity.ok().body(admin);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert (@RequestBody Admin admin){
		admin = service.insert(admin);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(admin.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update (@RequestBody Admin admin, @PathVariable String id){
		admin.setId(id);
		admin = service.update(admin);
		return ResponseEntity.noContent().build();
	}
	
}
