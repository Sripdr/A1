package in.shop.jwt;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidate {
    public static List<String> endpoints = List.of(
            "/shop/authapi/auth/**",
            /*"/shop/authapi/auth/registerAdmin",
            "/shop/authapi/auth/registerUser",
            "/shop/authapi/auth/login",*/
            "/shop/product/**"
    );

    public Predicate<ServerHttpRequest> isSecureEndpoint= request ->
            endpoints.stream()
                    .anyMatch(endpointUri -> request.getURI().getPath().equals(endpointUri));

}
