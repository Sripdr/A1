package in.shop.repository;

import in.shop.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser,String> {

    public Optional<AuthUser> findByEmail(String email);

    public Optional<AuthUser> findByPhoneNumber(String phoneNumber);

    public Optional<AuthUser> findByEmailOrPhoneNumber(String email,String phoneNumber);

}
