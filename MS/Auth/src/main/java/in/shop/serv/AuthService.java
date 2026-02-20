package in.shop.serv;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;
import in.shop.excep.UserExist;

import java.util.List;

public interface AuthService {
    public AuthRes register(AuthReq authReq) throws UserExist;
    public AuthRes findByUsername(String username);
    public AuthRes update(Long userId,AuthReq authReq);
    public AuthRes findById(Long userId);
    public List<AuthRes> findAll();
    public void deleteById(Long userId);
}
