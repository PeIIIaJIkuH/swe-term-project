package com.hotel.repository;

import com.hotel.models.ERole;
import com.hotel.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	boolean existsByName(ERole roleEmployee);
}
