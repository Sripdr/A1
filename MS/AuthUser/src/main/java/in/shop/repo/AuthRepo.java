package in.shop.repo;

import in.shop.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepo extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
}
