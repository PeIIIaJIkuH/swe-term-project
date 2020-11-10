package com.hotel.controllers;

import com.hotel.models.EType;
import com.hotel.models.Guest;
import com.hotel.models.Type;
import com.hotel.payload.request.GuestRegisterRequest;
import com.hotel.payload.request.LoginRequest;
import com.hotel.payload.response.GuestJwtResponse;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.GuestRepository;
import com.hotel.repository.TypeRepository;
import com.hotel.security.jwt.JwtUtils;
import com.hotel.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/guest")
public class GuestAuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl guestDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> types = guestDetails.getAuthorities().stream()
                .map(type -> type.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new GuestJwtResponse(jwt,
                guestDetails.getId(),
                guestDetails.getUsername(),
                guestDetails.getEmail(),
                guestDetails.getFirstName(),
                guestDetails.getLastName(),
                guestDetails.getHomePhoneNumber(),
                guestDetails.getMobilePhoneNumber(),
                guestDetails.getCountry(),
                guestDetails.getCity(),
                guestDetails.getStreet(),
                guestDetails.getIdType(),
                guestDetails.getIdNumber(),
                types));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerGuest(@Valid @RequestBody GuestRegisterRequest guestRegisterRequest) {
        if (guestRepository.existsByUsername(guestRegisterRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (guestRepository.existsByEmail(guestRegisterRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Guest guest = new Guest(guestRegisterRequest.getUsername(),
                guestRegisterRequest.getEmail(),
                encoder.encode(guestRegisterRequest.getPassword()),
                guestRegisterRequest.getFirstName(),
                guestRegisterRequest.getLastName(),
                guestRegisterRequest.getHomePhoneNumber(),
                guestRegisterRequest.getMobilePhoneNumber(),
                guestRegisterRequest.getCountry(),
                guestRegisterRequest.getCity(),
                guestRegisterRequest.getStreet(),
                guestRegisterRequest.getIdType(),
                guestRegisterRequest.getIdNumber());

        Set<String> strTypes = guestRegisterRequest.getTypes();
        Set<Type> types = new HashSet<>();

        if (strTypes == null) {
            Type guestType = typeRepository.findByName(EType.TYPE_GUEST)
                    .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
            types.add(guestType);
        } else {
            strTypes.forEach(type -> {
                switch (type) {
                    case "bronze":
                        Type bronzeType = typeRepository.findByName(EType.TYPE_BRONZE)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(bronzeType);
                        break;
                    case "silver":
                        Type silverType = typeRepository.findByName(EType.TYPE_SILVER)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(silverType);
                        break;
                    case "gold":
                        Type goldType = typeRepository.findByName(EType.TYPE_GOLD)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(goldType);
                        break;
                    case "vip":
                        Type vipType = typeRepository.findByName(EType.TYPE_VIP)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(vipType);
                        break;
                    case "military":
                        Type militaryType = typeRepository.findByName(EType.TYPE_MILITARY)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(militaryType);
                        break;
                    case "government":
                        Type governmentType = typeRepository.findByName(EType.TYPE_GOVERNMENT)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(governmentType);
                        break;
                    default:
                        Type guestType = typeRepository.findByName(EType.TYPE_GUEST)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(guestType);
                }
            });
        }

        guest.setTypes(types);
        guestRepository.save(guest);

        return ResponseEntity.ok(new MessageResponse("Guest registered successfully!"));
    }
}
