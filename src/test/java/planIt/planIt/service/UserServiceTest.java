package planIt.planIt.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import planIt.planIt.common.exeption.CustomException;
import planIt.planIt.controller.dto.user.UserDTO;
import planIt.planIt.controller.dto.user.UserIdSearchDTO;
import planIt.planIt.controller.dto.user.UserPwSearchDTO;
import planIt.planIt.domain.User;
import org.junit.jupiter.api.Test;
import planIt.planIt.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void saveTest(){
        //given
        UserDTO userDTO = UserDTO.builder()
                .userId("testId")
                .pw("testPw")
                .name("홍길동")
                .phoneNumber("01012345678")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        User user = User.builder()
                .userId(userDTO.getUserId())
                .pw(userDTO.getPw())
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .birth(userDTO.getBirth())
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        User saveUser = userService.save(userDTO);

        //then
        assertNotNull(saveUser);
        assertEquals("testId", saveUser.getUserId());
        assertEquals("홍길동", saveUser.getName());
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void userIdSearchTest(){
        //given
        User user = User.builder()
                .userId("testId")
                .pw("testPw")
                .name("홍길동")
                .phoneNumber("01012345678")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        UserIdSearchDTO userIdSearchDTO = UserIdSearchDTO.builder()
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userIdSearchDTO.getName(),
                userIdSearchDTO.getBirth(),
                userIdSearchDTO.getEmail())
        ).thenReturn(user);

        //when
        User userIdSearch = userService.userIdSearch(userIdSearchDTO);

        //then
        assertNotNull(userIdSearch);
        assertEquals(user.getUserId(), userIdSearch.getUserId());

    }

    @Test
    void userIdSearchTest_NotFoundFail(){
        //given
        UserIdSearchDTO userIdSearchDTO = UserIdSearchDTO.builder()
                .name("누구세요")
                .email("failtest@email.com")
                .birth("123456")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userIdSearchDTO.getName(),
                userIdSearchDTO.getBirth(),
                userIdSearchDTO.getEmail()
        )).thenReturn(null);

        //when & Then
        assertThrows(CustomException.class, () -> userService.userIdSearch(userIdSearchDTO));

    }

    @Test
    void userPwSearchTest(){

        //given
        User user = User.builder()
                .userId("testId")
                .pw("testPw")
                .name("홍길동")
                .phoneNumber("01012345678")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("testId")
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userPwSearchDTO.getName(),
                userPwSearchDTO.getBirth(),
                userPwSearchDTO.getEmail()
        )).thenReturn(user);

        //when
        User userPwSearch = userService.userPwSearch(userPwSearchDTO);

        //then
        assertEquals(userPwSearchDTO.getUserId(), userPwSearch.getUserId());
        assertEquals(userPwSearchDTO.getName(), userPwSearch.getName());
    }

    @Test
    void setNewPwTest(){

        //given
        User user = User.builder()
                .userId("testId")
                .pw("testPw")
                .name("홍길동")
                .phoneNumber("01012345678")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("testId")
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .newPw("newPwTest")
                .newPwCheck("newPwTest")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userPwSearchDTO.getName(),
                userPwSearchDTO.getBirth(),
                userPwSearchDTO.getEmail()
        )).thenReturn(user);

        when(userRepository.save(any(User.class))).thenReturn(user);

        //when
        User setNewPw = userService.setNewPw(userPwSearchDTO);

        //then
        assertEquals(userPwSearchDTO.getNewPw(), setNewPw.getPw());
        assertEquals(userPwSearchDTO.getNewPwCheck(), setNewPw.getPw());
        verify(userRepository, times(1)).save(any(User.class));

    }

    @Test
    void userPwSearchTest_NotFoundFail(){

        //given
        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("failTest")
                .name("누구세요")
                .email("failtest@email.com")
                .birth("123456")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userPwSearchDTO.getName(),
                userPwSearchDTO.getBirth(),
                userPwSearchDTO.getEmail()
        )).thenReturn(null);

        //when & Then
        assertThrows(CustomException.class, () -> userService.userPwSearch(userPwSearchDTO));

    }

    @Test
    void setNewPw_missMatchFail(){

        //given
        User user = User.builder()
                .userId("testId")
                .pw("testPw")
                .name("홍길동")
                .phoneNumber("01012345678")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("testId")
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .newPw("newPwTest")
                .newPwCheck("newPwTestFail")
                .build();

        when(userRepository.findByNameAndBirthAndEmail(
                userPwSearchDTO.getName(),
                userPwSearchDTO.getBirth(),
                userPwSearchDTO.getEmail()
        )).thenReturn(user);

        //when & then
        assertThrows(CustomException.class, () -> userService.setNewPw(userPwSearchDTO));
    }

}
