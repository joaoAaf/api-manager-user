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
import apiManagerUser.dto.UserPost;
import apiManagerUser.dto.UserView;
import apiManagerUser.services.UserService;
import jakarta.validation.Valid;

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
		return ResponseEntity.ok().body(new UserView(user));
	}

	@PostMapping
	public ResponseEntity<Void> postUser(@RequestBody @Valid UserPost userPost) {
		User newUser = service.fromDTO(userPost);
		newUser = service.insert(newUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		service.delete(loggedInUserId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestBody @Valid UserMod userMod) {
		User newUser = service.fromDTO(userMod);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		newUser.setId(loggedInUserId);
		newUser = service.update(newUser);
		return ResponseEntity.noContent().build();
	}

}
