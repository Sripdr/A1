package in.shop.repository;

import in.shop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Users,Long> {

    public Optional<Users> findByUsrId(String usrId);

    public Optional<Users> findByUsername(String username);

    public Optional<Users> findByEmail(String email);

    public Optional<Users> findByPhoneNumber(String phoneNumber);

    public Optional<Users> findByEmailOrPhoneNumber(String email, String phoneNumber);

}
