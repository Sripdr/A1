package in.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceJWtAuthenticationApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserServiceJWtAuthenticationApplication.class, args);
	}

}
