package pl.notes.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<pl.notes.user.User, Long> {
    Optional<pl.notes.user.User> findByEmail(String email);
    boolean existsByEmail(String email);
}
