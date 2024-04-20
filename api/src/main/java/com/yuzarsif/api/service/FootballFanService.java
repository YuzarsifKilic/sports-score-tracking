package com.yuzarsif.api.service;

import com.yuzarsif.api.dto.CreateFootballFanRequest;
import com.yuzarsif.api.model.FootballFan;
import com.yuzarsif.api.repository.FootballFanRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FootballFanService {

    private final FootballFanRepository footballFanRepository;
    private final PasswordEncoder passwordEncoder;

    public FootballFanService(FootballFanRepository footballFanRepository, PasswordEncoder passwordEncoder) {
        this.footballFanRepository = footballFanRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createFootballFan(CreateFootballFanRequest request) {
        FootballFan footballFan = FootballFan
                .builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .build();

        footballFanRepository.save(footballFan);
    }
}
