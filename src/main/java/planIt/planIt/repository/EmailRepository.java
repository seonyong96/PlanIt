package planIt.planIt.repository;

import jakarta.persistence.PreUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planIt.planIt.domain.Email;
import planIt.planIt.domain.User;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findByEmail(String email);
}
