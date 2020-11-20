package com.hotel;

import com.hotel.models.*;
import com.hotel.repository.RoleRepository;
import com.hotel.repository.SeasonRepository;
import com.hotel.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Date;

@SpringBootApplication
public class HotelApplication {
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	TypeRepository typeRepository;

	@Autowired
	SeasonRepository seasonRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}

	@EventListener
	public void initializeDB(ApplicationReadyEvent event) {
		roleRepository.save(new Role(ERole.ROLE_EMPLOYEE));
		roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_MANAGER));
		roleRepository.save(new Role(ERole.ROLE_CLEANING));
		roleRepository.save(new Role(ERole.ROLE_RECEPTIONIST));

		typeRepository.save(new Type(EType.TYPE_GUEST, 0));
		typeRepository.save(new Type(EType.TYPE_BRONZE, 1));
		typeRepository.save(new Type(EType.TYPE_SILVER, 3));
		typeRepository.save(new Type(EType.TYPE_GOLD, 5));
		typeRepository.save(new Type(EType.TYPE_MILITARY, 9));
		typeRepository.save(new Type(EType.TYPE_GOVERNMENT, 7));
		typeRepository.save(new Type(EType.TYPE_VIP, 10));

		seasonRepository.save(new Season("winter", new Date(120, 11, 1),
				new Date(121, 1, 28), 1.2F));
		seasonRepository.save(new Season("spring", new Date(120, 2, 1),
				new Date(120, 4, 31), 1.0F));
		seasonRepository.save(new Season("summer", new Date(120, 5, 1),
				new Date(120, 7, 30), 0.8F));
		seasonRepository.save(new Season("autumn", new Date(120, 8, 1),
				new Date(120, 10, 30), 0.9F));
	}
}
