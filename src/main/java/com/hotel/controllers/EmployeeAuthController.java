package com.hotel.controllers;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.ERole;
import com.hotel.models.Employee;
import com.hotel.models.Hotel;
import com.hotel.models.Role;
import com.hotel.payload.request.EmployeeRegisterRequest;
import com.hotel.payload.request.LoginRequest;
import com.hotel.payload.response.GuestJwtResponse;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.EmployeeRepository;
import com.hotel.repository.HotelRepository;
import com.hotel.repository.RoleRepository;
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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeAuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired HotelRepository hotelRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl employeeDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = employeeDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new GuestJwtResponse(jwt,
                employeeDetails.getId(),
                employeeDetails.getUsername(),
                employeeDetails.getEmail(),
                employeeDetails.getFirstName(),
                employeeDetails.getLastName(),
                employeeDetails.getHomePhoneNumber(),
                employeeDetails.getMobilePhoneNumber(),
                employeeDetails.getCountry(),
                employeeDetails.getCity(),
                employeeDetails.getStreet(),
                employeeDetails.getIdType(),
                employeeDetails.getIdNumber(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerGuest(@Valid @RequestBody EmployeeRegisterRequest employeeRegisterRequest) {
        if (employeeRepository.existsByUsername(employeeRegisterRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (employeeRepository.existsByEmail(employeeRegisterRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Employee employee = new Employee(employeeRegisterRequest.getUsername(),
                employeeRegisterRequest.getEmail(),
                encoder.encode(employeeRegisterRequest.getPassword()),
                employeeRegisterRequest.getFirstName(),
                employeeRegisterRequest.getLastName(),
                employeeRegisterRequest.getHomePhoneNumber(),
                employeeRegisterRequest.getMobilePhoneNumber(),
                employeeRegisterRequest.getCountry(),
                employeeRegisterRequest.getCity(),
                employeeRegisterRequest.getStreet(),
                employeeRegisterRequest.getIdType(),
                employeeRegisterRequest.getIdNumber());

        String hotelName = employeeRegisterRequest.getHotelName();
        Hotel hotel = (Hotel) hotelRepository.findByName(hotelName).orElseThrow(() -> new HotelNotFoundException("No hotel with name: " + hotelName));
        employee.setHotel(hotel);

        Set<String> strRoles = employeeRegisterRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(employeeRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "manager":
                        Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(managerRole);
                        break;
                    case "receptionist":
                        Role receptionistRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(receptionistRole);
                        break;
                    case "cleaning":
                        Role cleaningRole = roleRepository.findByName(ERole.ROLE_CLEANING)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(cleaningRole);
                        break;
                    default:
                        Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(employeeRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new MessageResponse("Employee registered successfully!"));
    }
}