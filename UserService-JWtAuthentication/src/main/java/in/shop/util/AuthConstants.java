package in.shop.util;

public class AuthConstants {
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String EXIST="Given is Already Exist In Database with ";
    public static final String NOT_FOUND=" User not found with given authId :";
    public static final String BASE_URL = "/api/shop/auth-service";
    public static final String REQUEST="request to find user by";
    public static final String[] PUBLIC={
           BASE_URL+"/public/**",
            "/v3/api-docs",
            "/swagger-ui"
    };
    public static final String ADMIN_URL=BASE_URL+"/admin";
    public static final String USER_URL=BASE_URL+"/user";
}
