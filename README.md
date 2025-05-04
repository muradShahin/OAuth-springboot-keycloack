# Spring Boot With OAUTH Using KeyClock

### the purpose of this Demo is to authenticate our spring boot applicaiton using OAUTH 
### to establish that we need to a token provider like keyclock

## First Step (Running Keycloak using Docker)

### use the below command to install and run keycloak in your machine  and make sure that docker is up and running 

```angular2html
docker run --name keycloak -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.2 start-dev
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

```angular2html
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

```angular2html
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

## 7th stpe we need to configure the issuer URL in our spring boot application properties to allow the applicaiton to validate the token 

```angular2html
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/demo
```


