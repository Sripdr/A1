package in.shop.service;



import in.shop.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    public UserResponseDto findUserById(Long userId);
    public List<UserResponseDto> findAllUsers();
    public UserResponseDto updateUserFirstName(Long userId, String firstName);
    public UserResponseDto updateUserLastName(Long userId, String lastName);
    public  UserResponseDto updateUserPhoneNumber(Long userId, String phoneNumber);
    public   UserResponseDto updateUserDateOfBirth(Long userId, String dateOfBirth);

}
