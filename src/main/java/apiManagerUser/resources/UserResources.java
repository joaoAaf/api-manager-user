package apiManagerUser.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apiManagerUser.domain.User;
import apiManagerUser.dto.UserMod;
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

	@PostMapping
	public ResponseEntity<Void> postUser(@RequestBody UserMod userMod) {
		User newUser = service.fromDTO(userMod);
		newUser = service.insertUser(newUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

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
