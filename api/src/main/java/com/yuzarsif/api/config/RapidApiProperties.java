package com.yuzarsif.api.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sport-api")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RapidApiProperties {

    private String xRapidApiKey;
    private String xRapidApiFootballHost;
    private String xRapidApiNbaHost;
    private String xRapidApiFormula1Host;
}
