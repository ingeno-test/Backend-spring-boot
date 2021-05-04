package com.springboot.backend.app.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.app.exeption.ResourceNotFoundException;
import com.springboot.backend.app.model.Comment;
import com.springboot.backend.app.model.User;
import com.springboot.backend.app.repository.CommentRepository;
import com.springboot.backend.app.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/comments")
	public List<Comment> getAllComments(@RequestParam String token){
		if(!Objects.isNull(token)) {
			User user = userRepository.findByToken(token);
			if(!Objects.isNull(user)) {
				return (List<Comment>) commentRepository.findAll();
			}
		}
		return null;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/comments")
	public Comment createComment(@RequestParam String username, @RequestBody Comment com, @RequestParam Long idCar, @RequestParam String token){
		if(!Objects.isNull(token)) {
			User user = userRepository.findByToken(token);
			if(!Objects.isNull(user)) {
				Comment comment = new Comment();
				comment.setIdCar(idCar);
				comment.setUsername(username);
				comment.setMessage(com.getMessage());
				comment.setDate(new Date(Calendar.getInstance().getTime().getTime()));
				return commentRepository.save(comment);
			}
		}
		return null;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable Long id, @RequestParam String token){
		if(!Objects.isNull(token)) {
			User user = userRepository.findByToken(token);
			if(!Objects.isNull(user)) {
				Comment comment = commentRepository.findById(id)
						.orElseThrow(()-> new ResourceNotFoundException("Comment qui a l'id="+id+" n'existe pas"));
				return ResponseEntity.ok(comment);
			}
		}
		
		return null;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/comments/{id}")
	public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment commentDetails, @RequestParam String token){
		if(!Objects.isNull(token)) {
			User user = userRepository.findByToken(token);
			if(!Objects.isNull(user)) {
				Comment comment = commentRepository.findById(id)
						.orElseThrow(()-> new ResourceNotFoundException("Comment qui a l'id="+id+" n'existe pas"));
				
				comment.setMessage(commentDetails.getMessage());
				
				Comment updatedComment = commentRepository.save(comment);
				
				return ResponseEntity.ok(updatedComment);
			}
		}
		
		return null;
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Map<String, Boolean>> delCommentById(@PathVariable Long id, @RequestParam String token){
		if(!Objects.isNull(token)) {
			User user = userRepository.findByToken(token);
			if(!Objects.isNull(user)) {
				Comment comment = commentRepository.findById(id)
						.orElseThrow(()-> new ResourceNotFoundException("Comment qui a l'id="+id+" n'existe pas"));
				commentRepository.delete(comment);
				Map<String, Boolean> response = new HashMap<String, Boolean>();
				response.put("deleted", Boolean.TRUE);
				return ResponseEntity.ok(response);
			}
		}
		
		return null;
	}
}
