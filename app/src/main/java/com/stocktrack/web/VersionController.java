package com.stocktrack.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("versionInfoController")
public class VersionController {

    private final ObjectProvider<BuildProperties> buildPropertiesProvider;
    private final Environment environment;

    public VersionController(ObjectProvider<BuildProperties> buildPropertiesProvider, Environment environment) {
        this.buildPropertiesProvider = buildPropertiesProvider;
        this.environment = environment;
    }

    @GetMapping("/api/version-info")
    public ResponseEntity<Map<String, Object>> version() {
        Map<String, Object> payload = new HashMap<>();
        BuildProperties bp = buildPropertiesProvider.getIfAvailable();
        String name = bp != null ? bp.getName() : environment.getProperty("spring.application.name", "stocktrack");
        String version = bp != null ? bp.getVersion() : environment.getProperty("revision", "dev");
        Object time = bp != null ? bp.getTime() : null;
        payload.put("name", name);
        payload.put("version", version);
        payload.put("time", time);
        return ResponseEntity.ok(payload);
    }
}
