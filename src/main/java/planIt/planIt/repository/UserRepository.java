package planIt.planIt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import planIt.planIt.domain.User;

import java.util.Optional;

//회원가입
//로그인
//로그아웃
//ID 찾기
//PW 찾기
//PW 변경
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //<S extends T> S save(S entity);
    User save(User user);
    //Optional<T> findById(ID id);
    Optional<User> findByName(String id);

}
