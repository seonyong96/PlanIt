package planIt.planIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import planIt.planIt.controller.UserDTO;
import planIt.planIt.domain.User;
import planIt.planIt.repository.UserRepository;

// jwt

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
// 빌더패턴 사용해서 UserDTO 로 받기
    public void save(User user) {
        duplicateCheck(user);
        user.setPw(encodePassword(user.getPw()));
        userRepository.save(user);
    }

    // id 중복체크
    private void duplicateCheck(User user) {
        userRepository.findByName(user.getId()).ifPresent(m -> {
            throw new IllegalStateException("이미 사용중인 ID 입니다." + user.getId());
        });
    }

    // pw BCrypt 인코딩
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    // 로그인 시 pw 검증 1234
    public boolean matchesPassword(String rawPassword, String encodePassword) {
        return passwordEncoder.matches(rawPassword, encodePassword);
    }

}