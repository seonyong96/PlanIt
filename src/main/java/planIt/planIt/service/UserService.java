package planIt.planIt.service;

import lombok.Builder;
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

    /**
     * 회원가입 서비스
     */
    //TODO createTime, modifyTime, role 구현 ( 최초 admin 이 user 를 admin 으로 지정 )
    public User save(UserDTO dto) {

        User user = User.builder().userId(dto.getUserId())
//                .pw(encodePassword(dto.getPw()))
                .pw(dto.getPw())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .build();

        duplicateCheck(user);
        return userRepository.save(user);

    }

    // id 중복체크
    private void duplicateCheck(User user) {
        userRepository.findByUserId(user.getUserId()).ifPresent(m -> {
            throw new IllegalStateException(user.getUserId() + "는(은) 이미 사용중인 ID 입니다.");
        });
    }

    // pw BCrypt 인코딩
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // 로그인 시 pw 검증
    public boolean matchesPassword(String rawPassword, String encodePassword) {
        return passwordEncoder.matches(rawPassword, encodePassword);
    }

}