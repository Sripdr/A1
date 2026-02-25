package in.shop.serv;

import in.shop.dto.AuthRequest;
import in.shop.dto.AuthResponse;
import in.shop.entity.AuthUser;

import java.util.List;

public interface AdminService {
    List<AuthResponse> findAll();
   public AuthResponse findById(Long userId);
    void deleteById(Long userId);
    public AuthResponse findByUsername(String username);
    public AuthResponse findByPhoneNumber(String phoneNumber);
    public AuthResponse updateById(Long userId, AuthRequest authRequest);
    public AuthResponse updateByPhoneNumber(String phoneNumber, AuthRequest authRequest);
    public AuthResponse updateByUsername(String username, AuthRequest authRequest);
}
