package planIt.planIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import planIt.planIt.common.enums.ErrorCode;
import planIt.planIt.common.exeption.CustomException;
import planIt.planIt.controller.dto.UserDTO;
import planIt.planIt.controller.dto.UserIdSearchDTO;
import planIt.planIt.controller.dto.UserPwSearchDTO;
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
//            throw new IllegalStateException(user.getUserId() + "는(은) 이미 사용중인 ID 입니다.");
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

}