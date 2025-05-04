# 🛡️ Spring Boot with OAuth2 Using Keycloak

This project demonstrates how to secure a Spring Boot REST API using **OAuth2** with **Keycloak** as the identity and token provider.

---

## ✅ Objective

The goal of this demo is to:
- Secure Spring Boot APIs using OAuth2
- Use Keycloak to issue and validate JWT tokens
- Enforce authentication and authorization in the application

---

## 🚀 Step 1: Run Keycloak via Docker

Ensure Docker is installed and running on your machine. Use the command below to start Keycloak in development mode:

```bash
docker run --name keycloak -p 8080:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:26.2 start-dev

```
## Second Step  : Create a realm in keycloak 

## Third Step : Create a client id following the below configuration 

```angular2html
Client authentication : ON
Authorization : OFF
Standard flow :  ON
Service accounts roles : ON 

```

## 4th step try to get a token by calling the below CURL:

```
curl --location 'http://localhost:8080/realms/{your realm name}/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=7B05C5BD99CEE73DC950D126775DEEF8' \
--data-urlencode 'client_id=client-id' \
--data-urlencode 'client_secret=CLIENT-SECRET-VALUE' \
--data-urlencode 'grant_type=client_credentials'
```


## 5th step is to enable spring security in our spring boot application by adding the below dependancies

```angular2html
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>

```

## 6th step we need to create a security configuration class to enable authentication on of our APIs 

```
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // 🔐 Require auth on all requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}

```

## 7th step we need to configure the issuer URL in our spring boot application properties to allow the applicaiton to validate the token 

```
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/demo
```


