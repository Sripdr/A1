package in.shop.serv;

import in.shop.dto.AuthReq;
import in.shop.dto.AuthRes;

public interface UserService {

    public AuthRes findByUsername(String username);
    public AuthRes update(Long userId,AuthReq authReq);
    public AuthRes findById(Long userId);
        public AuthRes update(String username,AuthReq authReq);

}
