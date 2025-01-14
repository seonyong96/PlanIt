package repository;
import domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//회원가입
//로그인
//로그아웃
//ID 찾기
//PW 찾기
//PW 변경

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String id);
}
