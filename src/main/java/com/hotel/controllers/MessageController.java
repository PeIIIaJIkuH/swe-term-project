package com.hotel.controllers;

import com.hotel.models.Employee;
import com.hotel.models.Guest;
import com.hotel.models.relations.Message;
import com.hotel.payload.request.MessageRequest;
import com.hotel.payload.response.GuestMessageResponse;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.EmployeeRepository;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
	@Autowired
	MessageRepository messageRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	GuestRepository guestRepository;

	private GuestMessageResponse toResponse(Message message) {
		GuestMessageResponse response = new GuestMessageResponse(message.getId(),
				message.getDate(),
				message.getText(),
				message.getManager().getId(),
				message.getGuest().getId());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllMessages() {
		List<GuestMessageResponse> messages = new ArrayList<>();
		messageRepository.findAll().forEach(message -> {
			messages.add(toResponse(message));
		});
		return ResponseEntity.ok(messages);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMessage(@PathVariable Long id) {
		if (!messageRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Message is not found!"));
		}
		Message message = messageRepository.findById(id).get();
		return ResponseEntity.ok(toResponse(message));
	}

	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	@PostMapping
	public ResponseEntity<?> createMessage(@Valid @RequestBody MessageRequest request) {
		if (!employeeRepository.existsById(request.getManagerId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Manager is not found!"));
		}
		if (!guestRepository.existsById(request.getGuestId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Guest is not found!"));
		}
		Employee manager = employeeRepository.findById(request.getManagerId()).get();
		Guest guest = guestRepository.findById(request.getGuestId()).get();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Message message = new Message(now, request.getText());
		message.setManager(manager);
		message.setGuest(guest);
		messageRepository.save(message);
		return ResponseEntity.ok(new MessageResponse("Message created successfully!"));
	}
}
