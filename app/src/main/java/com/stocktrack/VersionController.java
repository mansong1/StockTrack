package com.stocktrack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@RestController
public class VersionController {
  @GetMapping("/api/version")
  public Map<String, String> version() throws IOException {
    Properties p = new Properties();
    try (var in = getClass().getClassLoader().getResourceAsStream("version.properties")) {
      if (in != null) p.load(in);
    }
    return Map.of(
        "name", "StockTrack",
        "version", p.getProperty("stocktrack.version", "unknown"),
        "sequence", p.getProperty("stocktrack.sequence", "unknown"),
        "commit", p.getProperty("stocktrack.commit", "unknown")
    );
  }
}
