package com.hotel.security.services;

import com.hotel.models.Guest;
import com.hotel.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class GuestDetailsServiceImpl implements UserDetailsService {
    @Autowired
    GuestRepository guestRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Guest guest = guestRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Guest Not Found with username: " + username));

        return UserDetailsImpl.build(guest);
    }
}
