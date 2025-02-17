
package planIt.planIt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import planIt.planIt.domain.User;

import javax.swing.text.html.Option;
import java.util.Optional;

//회원가입
//로그인
//로그아웃
//ID 찾기
//PW 찾기
//PW 변경
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String id);
    User findByNameAndBirthAndEmail(String name, String birth, String email);
    Optional<User> findByVerificationToken(String token);
    Optional<User> findByEmail(String email);

}
