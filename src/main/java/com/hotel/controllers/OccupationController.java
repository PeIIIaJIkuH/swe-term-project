package com.hotel.controllers;

import com.hotel.models.*;
import com.hotel.models.relations.Occupation;
import com.hotel.payload.request.OccupationRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.payload.response.OccupationResponse;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/occupations")
public class OccupationController {
	@Autowired
	OccupationRepository occupationRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	GuestRepository guestRepository;

	private OccupationResponse toResponse(Occupation occupation) {
		OccupationResponse response = new OccupationResponse(occupation.getId(),
				occupation.getStart(),
				occupation.getEnd(),
				occupation.getPayment(),
				occupation.getHotel().getId(),
				occupation.getRoom().getId(),
				occupation.getReceptionist().getId());
		if (occupation.getGuest() != null)
			response.setGuestId(occupation.getGuest().getId());
		else
			response.setGuestInfo(occupation.getGuestInfo());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllOccupations() {
		List<OccupationResponse> occupations = new ArrayList<>();
		occupationRepository.findAll().forEach(occupation -> {
			occupations.add(toResponse(occupation));
		});
		return ResponseEntity.ok(occupations);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOccupation(@PathVariable Long id) {
		if (!occupationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Occupation is not found!"));
		}
		Occupation occupation = occupationRepository.findById(id).get();
		return ResponseEntity.ok(occupation);
	}

	@PostMapping
	public ResponseEntity<?> createOccupation(@Valid @RequestBody OccupationRequest request) {
		if (!hotelRepository.existsById(request.getHotelId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		if (!roomRepository.existsById(request.getRoomId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not found!"));
		}
		String username = request.getGuestUsername();
		if (username != null) {
			if (!guestRepository.existsByUsername(username)) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Username is not found!"));
			}
		}
		if (!employeeRepository.existsById(request.getReceptionistId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Employee is not found!"));
		}
		Hotel hotel = hotelRepository.findById(request.getHotelId()).get();
		Room room = roomRepository.findById(request.getRoomId()).get();
		if (!room.getEmpty()) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not empty!"));
		}
		room.setEmpty(false);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Guest guest = null;
		if (username != null)
			guest = guestRepository.findByUsername(username).get();
		Employee receptionist = employeeRepository.findById(request.getReceptionistId()).get();
		Occupation occupation = new Occupation(now);
		occupation.setHotel(hotel);
		occupation.setRoom(room);
		occupation.setReceptionist(receptionist);
		if (username != null) {
			occupation.setGuest(guest);
		} else {
			GuestInfo info = new GuestInfo(request.getGuestInfo().getFirstName(),
					request.getGuestInfo().getLastName(),
					request.getGuestInfo().getHomePhoneNumber(),
					request.getGuestInfo().getMobilePhoneNumber(),
					request.getGuestInfo().getCountry(),
					request.getGuestInfo().getCity(),
					request.getGuestInfo().getStreet(),
					request.getGuestInfo().getIdType(),
					request.getGuestInfo().getIdNumber());
			occupation.setGuestInfo(info);
		}
		room.setEmpty(false);
		roomRepository.save(room);
		occupationRepository.save(occupation);
		return ResponseEntity.ok(new MessageResponse("Occupation created successfully!"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> endOccupation(@PathVariable Long id) {
		if (!occupationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Occupation is not found!"));
		}
		Occupation occupation = occupationRepository.findById(id).get();
		if (occupation.getPayment() != 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Occupation can no longer be changed!"));
		}
		Timestamp now = new Timestamp(System.currentTimeMillis());
		occupation.setEnd(now);
		LocalDate start = new Date(occupation.getStart().getTime()).toLocalDate();
		LocalDate end = new Date(now.getTime()).toLocalDate();
		for (LocalDate date = start; date.isBefore(end.plusDays(1)); date = date.plusDays(1)) {
			DayOfWeek day = date.getDayOfWeek();
			RoomType roomType = occupation.getRoom().getType();
			if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
				occupation.setPayment(occupation.getPayment() + roomType.getPrice() * 1.2F);
			} else {
				occupation.setPayment(occupation.getPayment() + roomType.getPrice());
			}
		}
		Integer discount = 0;
		for (Type type : occupation.getGuest().getTypes()) {
			if (type.getDiscount() > discount) {
				discount = type.getDiscount();
			}
		}
		if (discount != 0) {
			occupation.setPayment(occupation.getPayment() * (100 - discount) / 100);
		}
		occupation.getRoom().setClear(false);
		occupation.getRoom().setEmpty(true);
		occupationRepository.save(occupation);
		return ResponseEntity.ok(toResponse(occupation));
	}
}
