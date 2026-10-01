# Real-Time Market and News Data Ingestion Platform

A runnable starter implementation of a streaming ingestion platform. It includes a Spring Boot REST API, a Kafka producer/consumer flow, PostgreSQL persistence, health/metrics endpoints, a local Docker Compose environment, and an AWS Terraform starter. It is a portfolio/demo implementation, not a production deployment or a claim that the scale figures in a resume have been load-tested.

## Architecture

```text
Feed Simulator / REST Client
          |
          v
  Spring Boot Ingestion API ---> Kafka topic: market-events
                                      |
                                      v
                             Spring Kafka Consumer
                                      |
                                      v
                                  PostgreSQL
                                      |
                            Actuator / Prometheus
```

## Requirements
- Java 17+
- Maven 3.9+
- Docker Desktop / Docker Engine (for Kafka and PostgreSQL)

## Run locally
1. Start dependencies: `docker compose up -d`
2. Run the service: `mvn spring-boot:run`
3. Health: `http://localhost:8080/actuator/health`
4. Submit an event:
```bash
curl -X POST http://localhost:8080/api/v1/events -H "Content-Type: application/json" \
  -d '{"source":"market-feed","symbol":"AAPL","eventType":"QUOTE","payload":{"price":221.4,"volume":1200}}'
```
5. Browse stored events: `http://localhost:8080/api/v1/events`

## Configuration
Set `SPRING_KAFKA_BOOTSTRAP_SERVERS`, `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD` to override local defaults.

## Project contents
- `src/main/java/.../api`: ingestion and query endpoints
- `src/main/java/.../messaging`: Kafka publishing and consumption
- `src/main/java/.../persistence`: JPA entity and repository
- `infra/terraform`: minimal EKS/S3/MSK infrastructure sketch; review and extend before use
- `.github/workflows/ci.yml`: Maven build/test workflow

## Notes
The included local stack uses Kafka in KRaft mode. Spark streaming, multi-region resilience, security hardening, real market-data provider integrations, and scale benchmarking are extension points rather than implemented claims.
