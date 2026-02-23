package in.shop.serv;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.dto.Login;
import in.shop.excep.UserExist;

import java.util.List;

public interface AuthService {
    public AuthRes register(AuthReq authReq) throws UserExist;
    public String login(Login login);
    public List<AuthRes> findAll();
    public void deleteById(Long userId);
}
