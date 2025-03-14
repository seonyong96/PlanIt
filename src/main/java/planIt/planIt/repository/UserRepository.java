
package planIt.planIt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import planIt.planIt.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    User findByNameAndBirthAndEmail(String name, String birth, String email);
    User findByUserIdAndPw(String userId, String pw);
}
