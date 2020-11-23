package com.hotel.controllers;

import com.hotel.models.Hotel;
import com.hotel.models.Season;
import com.hotel.payload.request.SeasonRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.HotelRepository;
import com.hotel.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/seasons")
public class SeasonController {
	@Autowired
	SeasonRepository seasonRepository;

	@Autowired
	HotelRepository hotelRepository;

	@GetMapping
	public ResponseEntity<?> getAllSeasons() {
		return ResponseEntity.ok(seasonRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getSeason(@PathVariable Long id) {
		if (!seasonRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Season is not found!"));
		}
		return ResponseEntity.ok(seasonRepository.findById(id).get());
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> createSeason(@Valid @RequestBody SeasonRequest request) throws ParseException {
		if (seasonRepository.existsByName(request.getName())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Name is already taken!"));
		}
		if (!request.getStart().before(request.getEnd())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect dates!"));
		}
		if (request.getPriceFactor() <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect price factor!"));
		}
		Season season = new Season(request.getName(),
				request.getStart(),
				request.getEnd(),
				request.getPriceFactor());
		seasonRepository.save(season);
		return ResponseEntity.ok(new MessageResponse("Season created successfully!"));
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSeason(@PathVariable Long id) {
		if (!seasonRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Season is not found!"));
		}
		Season season = seasonRepository.findById(id).get();
		for (Hotel hotel : hotelRepository.findAll()) {
			for (Season hotelSeason : hotel.getSeasons()) {
				if (hotelSeason.getName().equals(season.getName())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Season is linked with other hotels!"));
				}
			}
		}
		seasonRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Season deleted successfully!"));
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSeason(@PathVariable Long id, @Valid @RequestBody SeasonRequest request) throws ParseException {
		if (!seasonRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Season is not found!"));
		}
		if (!request.getStart().before(request.getEnd())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect dates!"));
		}
		if (request.getPriceFactor() <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect price factor!"));
		}
		Season season = seasonRepository.findById(id).get();
		season.setName(request.getName());
		season.setStart(request.getStart());
		season.setEnd(request.getEnd());
		season.setPriceFactor(request.getPriceFactor());
		seasonRepository.save(season);
		return ResponseEntity.ok(new MessageResponse("Season updated successfully!"));
	}
}
