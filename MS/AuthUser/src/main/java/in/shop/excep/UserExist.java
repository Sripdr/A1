package in.shop.excep;

public class UserExist extends RuntimeException {
    public UserExist(String message) {
        super(message);
    }
}
