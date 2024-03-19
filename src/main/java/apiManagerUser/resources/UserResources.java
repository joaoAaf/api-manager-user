package apiManagerUser.resources;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import apiManagerUser.domain.User;
import apiManagerUser.dto.Email;
import apiManagerUser.dto.UserMod;
import apiManagerUser.dto.UserPost;
import apiManagerUser.dto.UserView;
import apiManagerUser.services.EmailService;
import apiManagerUser.services.UserService;
import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@GetMapping
	public ResponseEntity<UserView> getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		User user = userService.findById(loggedInUserId);
		return ResponseEntity.ok().body(new UserView(user));
	}

	@PostMapping
	public ResponseEntity<Object> postUser(@RequestBody @Valid UserPost userPost) throws IOException {
		User newUser = userService.fromDTO(userPost);
		if (userService.findByEmail(newUser.getEmail()) != null) {
			return ResponseEntity.badRequest().body("Já Existe um Usuário com este Email!");
		}
		newUser = userService.insert(newUser);
		Email email = emailService.emailContent(newUser);
		emailService.sendEmail(email);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserView(newUser));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		userService.delete(loggedInUserId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestBody @Valid UserMod userMod) {
		User newUser = userService.fromDTO(userMod);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authentication.getName();
		newUser.setId(loggedInUserId);
		newUser = userService.update(newUser);
		return ResponseEntity.noContent().build();
	}

}
