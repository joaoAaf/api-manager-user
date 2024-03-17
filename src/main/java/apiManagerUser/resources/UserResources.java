package apiManagerUser.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apiManagerUser.domain.User;
import apiManagerUser.dto.UserView;
import apiManagerUser.services.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<UserView> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		User user = service.findById(loggedInUserId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(new UserView(user));
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public ResponseEntity<UserDTO> findById(@PathVariable String id) {
	// 	User user = service.findById(id);
	// 	return ResponseEntity.ok().body(new UserDTO(user));
	// }

	// @RequestMapping(method = RequestMethod.POST)
	// public ResponseEntity<Void> insert(@RequestBody User user) {
	// 	// User user = service.fromDTO(userDto);
	// 	user = service.insert(user);
	// 	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
	// 	return ResponseEntity.created(uri).build();
	// }

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// public ResponseEntity<Void> delete(@PathVariable String id) {
	// 	service.delete(id);
	// 	return ResponseEntity.noContent().build();
	// }

	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// public ResponseEntity<Void> update(@RequestBody User user, @PathVariable String id) {
	// 	// User user = service.fromDTO(userDto);
	// 	user.setId(id);
	// 	user = service.update(user);
	// 	return ResponseEntity.noContent().build();
	// }

}
