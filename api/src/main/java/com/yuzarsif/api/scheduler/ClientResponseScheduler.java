package com.yuzarsif.api.scheduler;

import com.yuzarsif.api.service.ClientResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
public class ClientResponseScheduler {

    private final ClientResponseService clientResponseService;

    public ClientResponseScheduler(ClientResponseService clientResponseService) {
        this.clientResponseService = clientResponseService;
    }

    @Scheduled(fixedDelay = 600000)
    public void deleteResponses() {
        log.info("Deleting response scheduler started...");
        clientResponseService.deleteAll();
        log.info("Deleting response scheduler finished...");
    }
}
