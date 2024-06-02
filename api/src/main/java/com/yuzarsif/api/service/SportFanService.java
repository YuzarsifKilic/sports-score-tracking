package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateFootballFanRequest;
import com.yuzarsif.api.dto.FootballFanDto;
import com.yuzarsif.api.exception.AuthenticationException;
import com.yuzarsif.api.exception.FootballFanNotFoundException;
import com.yuzarsif.api.model.SportFan;
import com.yuzarsif.api.model.Role;
import com.yuzarsif.api.repository.SportFanRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SportFanService {

    private final SportFanRepository sportFanRepository;
    private final PasswordEncoder passwordEncoder;

    public SportFanService(SportFanRepository sportFanRepository, PasswordEncoder passwordEncoder) {
        this.sportFanRepository = sportFanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createFootballFan(CreateFootballFanRequest request) {
        checkIfEmailExists(request.email());
        SportFan sportFan = SportFan
                .builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .role(Role.ROLE_FOOTBALL_FAN)
                .build();

        sportFanRepository.save(sportFan);
    }

    protected SportFan findById(Long id) {
        return sportFanRepository.findById(id)
                .orElseThrow(() -> new FootballFanNotFoundException("Football fan not found by id : " + id));
    }

    public FootballFanDto getById(Long id) {
        SportFan sportFan = findById(id);
        return FootballFanDto.convert(sportFan);
    }

    private void checkIfEmailExists(String email) {
        if (sportFanRepository.findByEmail(email).isPresent()) {
            throw new AuthenticationException("Email already exists");
        }
    }
}
