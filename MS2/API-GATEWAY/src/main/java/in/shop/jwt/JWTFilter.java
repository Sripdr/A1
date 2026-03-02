package in.shop.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class JWTFilter extends AbstractGatewayFilterFactory<JWTFilter> {
    @Autowired
private  RouteValidate  routeValidate;

    @Autowired
    private JWTService  jwtService;


    @Override
    public GatewayFilter apply(JWTFilter config) {
        return ((exchange, chain) -> {
                if (routeValidate.isSecureEndpoint.test((ServerHttpRequest) exchange.getRequest())){
                    if (!exchange.getRequest().getHeaders().containsHeader(HttpHeaders.AUTHORIZATION)){
                        throw new RuntimeException(" Missing Authorization Header");
                    }
                    String header = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                    if (header.startsWith("Bearer ")){
                        header = header.substring(7);
                    }
                    try {
                        jwtService.validateToken(header);
                    } catch (RuntimeException e) {
                        throw new RuntimeException("Unauthorised for Application");
                    }
                }
            return chain.filter(exchange);
        }

            );
}
    }
