package planIt.planIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import planIt.planIt.common.auth.JwtTokenProvider;
import planIt.planIt.common.enums.ErrorCode;
import planIt.planIt.common.enums.UserRole;
import planIt.planIt.common.exeption.CustomException;
import planIt.planIt.controller.dto.*;
import planIt.planIt.domain.User;
import planIt.planIt.repository.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

// jwt

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
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
        user.setRole(UserRole.ROLE_USER.getRoleName());
        return userRepository.save(user);

    }

    // id 중복체크
    private void duplicateCheck(User user) {
        userRepository.findByUserId(user.getUserId()).ifPresent(m -> {
            throw new CustomException(ErrorCode.DUPLICATE_ID);
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

    /**
     * 아이디 찾기
     * 이메일 인증 -> 이름 && 생년월일 && 이메일 로 ID찾기
     * @param dto
     * @return User
     */

    public User userIdSearch(UserIdSearchDTO dto){

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .build();

        User foundUser = userRepository.findByNameAndBirthAndEmail(user.getName(), user.getBirth(), user.getEmail());

        if (foundUser == null) {
            throw new CustomException(ErrorCode.NOTFOUND_ID);
        }

        return foundUser;

    }


    /** PW찾기
     *  처음 PW찾기 시 입력한 정보 validation 후 해당 User 리턴
     * @param dto
     * @return User
     */
    public User userPwSearch(UserPwSearchDTO dto){
//        User user = User.builder()
//                .name(dto.getName())
//                .userId(dto.getUserId())
//                .email(dto.getEmail())
//                .birth(dto.getBirth())
//                .build();
//
//        User foundPw = userRepository.findByNameAndBirthAndEmail(user.getName(), user.getBirth(), user.getEmail());
//
//        if (foundPw == null){
//            throw new CustomException(ErrorCode.NOTFOUND_PW);
//        }
//
//        return foundPw;

        return findUserByPw(dto);

    }

    /** 새 비밀번호 저장
     * 입력받은 새 비밀번호 DB저장
     * @param dto
     * @return User
     */
    public User setNewPw(UserPwSearchDTO dto){

        User user = findUserByPw(dto);
        user.setPw(varifyPw(dto));

        userRepository.save(user);

        return user;
    }

    public User findUserByPw(UserPwSearchDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .build();

        User foundPw = userRepository.findByNameAndBirthAndEmail(user.getName(), user.getBirth(), user.getEmail());

        if (foundPw == null){
            throw new CustomException(ErrorCode.NOTFOUND_PW);
        }

        return foundPw;
    }

    public String varifyPw(UserPwSearchDTO dto) {

        if(!dto.getNewPw().equals(dto.getNewPwCheck())){
            throw new CustomException(ErrorCode.MISSMATCH_PW);
        }

        return dto.getNewPw();
    }

    /** 로그인
     *
     * @param loginDTO
     * @return String(토큰값)
     */
    public String login(LoginDTO loginDTO) {
        User user = User.builder()
                .userId(loginDTO.getUserId())
                .pw(loginDTO.getPw())
                .build();

        User findUser = userRepository.findByUserIdAndPw(user.getUserId(), user.getPw());

        if(findUser == null) {
            throw new CustomException(ErrorCode.MISSMATCH_LOGIN);
        }

        return jwtTokenProvider.generateToken(findUser.getUserId());

    }

    /** Role 변경
     *
     * @param dto
     * @return User
     */
    public User roleChange(RoleChangeDTO dto) {

        User user = User.builder()
                .userId(dto.getUserId())
                .build();

//        User foundUser = userRepository.findByUserId(dto.getUserId());

        Optional<User> optionalUser = userRepository.findByUserId(dto.getUserId());

        User foundUser = optionalUser.orElseThrow( () ->
                new CustomException(ErrorCode.NOTFOUND_ID)
                );

        if (!dto.getNewRole().equals("ROLE_USER") && !dto.getNewRole().equals("ROLE_ADMIN")) {
            throw new IllegalArgumentException("유효하지 않은 역할입니다.");
        }

        if (dto.getNewRole().equals(foundUser.getRole())) {
            throw new CustomException(ErrorCode.DUPLICATE_ROLE);
        }


        foundUser.setRole(dto.getNewRole());
        userRepository.save(foundUser);

        return foundUser;

    }
}