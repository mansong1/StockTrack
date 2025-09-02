# StockTrack
Deploy JARs onto VMs using Ansible with a Harness Deployment Template

## Spring Boot Application

The `app/` directory contains a Spring Boot application scaffolded with Maven.

### Build

```
mvn -q -DskipTests package
```

Run from the `app/` directory.

### Run (dev)

```
mvn spring-boot:run
```

Then visit:

- Health: http://localhost:8080/actuator/health
- Hello: http://localhost:8080/api/hello

### Run (jar)

After building, run the fat jar:

```
java -jar target/stocktrack-0.0.1-SNAPSHOT.jar
```

### Tech

- Spring Boot 3.3.x
- Java 17
- Starters: Web, Actuator, Test

### Versioning (Harness CI)

The Maven project uses CI-friendly versions via the `${revision}` property in `app/pom.xml`.

- Default: `0.0.1-SNAPSHOT`.
- In Harness, set env var `HARNESS_SEQUENCE_ID` to inject a build number.
- When `HARNESS_SEQUENCE_ID` is present, the active Maven profile `harness-sequence-version` sets the version to `0.0.${HARNESS_SEQUENCE_ID}`.

Examples:

From your shell:

```
cd app
HARNESS_SEQUENCE_ID=42 mvn -q -DskipTests package
# Produces target/stocktrack-0.0.42.jar
```

Override manually without env var:

```
cd app
mvn -q -DskipTests -Drevision=1.2.3 package
# Produces target/stocktrack-1.2.3.jar
```

Runtime version endpoint:

- `/api/version` returns build name, version, time.
- `/actuator/info` also includes build info when built with Maven (build-info is generated during build).
