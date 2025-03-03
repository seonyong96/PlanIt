package planIt.planIt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.controller.dto.EmailDTO;
import planIt.planIt.controller.dto.UserDTO;
import planIt.planIt.controller.dto.UserIdSearchDTO;
import planIt.planIt.controller.dto.UserPwSearchDTO;
import planIt.planIt.domain.Email;
import planIt.planIt.domain.User;
import planIt.planIt.repository.UserRepository;
import planIt.planIt.service.EmailService;
import planIt.planIt.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(classes = UserController.class)
//@AutoConfigureMockMvc

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @MockBean
    private UserService userService;

    @MockBean
    private EmailService emailService;


    @Test
    @DisplayName("회원가입 성공 테스트")
    @WithMockUser
    void registerUserTest() throws Exception {

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
                .id(1L)
                .userId(userDTO.getUserId())
                .pw(userDTO.getPw())
                .name(userDTO.getName())
                .phoneNumber(userDTO.getPhoneNumber())
                .email(userDTO.getEmail())
                .birth(userDTO.getBirth())
                .build();

        when(userService.save(any())).thenReturn(user);

        String mapper = objectMapper.writeValueAsString(userDTO);

        //when & then
        mockMvc.perform(post("/register").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper)).andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("ID 찾기 성공 테스트")
    @WithMockUser
    void getUserIdSearchTest() throws Exception {

        //given
        UserIdSearchDTO userIdSearchDTO = UserIdSearchDTO.builder()
                .name("홍길동")
                .email("testEmail@test.com")
                .birth("250227")
                .build();

        User user = User.builder()
                .userId("test")
                .name(userIdSearchDTO.getName())
                .email(userIdSearchDTO.getEmail())
                .birth(userIdSearchDTO.getBirth())
                .build();


        String searchId = "test";

        when(userService.userIdSearch(any())).thenReturn(user);

        String mapper = objectMapper.writeValueAsString(userIdSearchDTO);

        //when & then
        mockMvc.perform(post("/userIdSearch").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper)).andDo(print())
                .andExpect(jsonPath("$.userId").value(searchId))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    @DisplayName("PW 찾기 성공 테스트")
    @WithMockUser
    void userPwSerachTest() throws Exception {

        //given
        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("testId")
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .build();

        User user = User.builder()
                .userId(userPwSearchDTO.getUserId())
                .name(userPwSearchDTO.getName())
                .email(userPwSearchDTO.getEmail())
                .birth(userPwSearchDTO.getBirth())
                .build();

        when(userService.userPwSearch(any())).thenReturn(user);

        String mapper = objectMapper.writeValueAsString(userPwSearchDTO);

        //when & then
        mockMvc.perform(post("/userPwSearch").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper)).andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("PW 재설정 성공 테스트")
    @WithMockUser
    void setNewPw() throws Exception {

        //given
        UserPwSearchDTO userPwSearchDTO = UserPwSearchDTO.builder()
                .userId("testId")
                .name("홍길동")
                .email("testEmail@email.com")
                .birth("250225")
                .newPw("test")
                .newPwCheck("test")
                .build();

        User user = User.builder()
                .userId(userPwSearchDTO.getUserId())
                .pw("testPw")
                .name(userPwSearchDTO.getName())
                .email(userPwSearchDTO.getEmail())
                .birth(userPwSearchDTO.getBirth())
                .build();


        when(userService.setNewPw(any())).thenReturn(user);

        String mapper = objectMapper.writeValueAsString(userPwSearchDTO);

        String newPw = userPwSearchDTO.getNewPw();
        user.setPw(newPw);

        //when & then
        mockMvc.perform(post("/setNewPw").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper)).andDo(print())
                .andExpect(jsonPath("$.pw").value(newPw))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("이메일 인증 요청 성공 테스트")
    @WithMockUser
    void mailSendTest() throws Exception {

        //given
        EmailDTO emailDTO = EmailDTO.builder()
                .email("testEmail@Email.com")
                .number(123456)
                .build();

        Email email = Email.builder()
                .email(emailDTO.getEmail())
                .number(emailDTO.getNumber())
                .build();

        when(emailService.sendMail(any())).thenReturn(email);

        String mapper = objectMapper.writeValueAsString(emailDTO);

        //when & then
        mockMvc.perform(post("/mailSend").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("이메일 인증번호 검증 성공 테스트")
    @WithMockUser
    void mailNumberCheckTest() throws Exception {

        //given
        EmailDTO emailDTO = EmailDTO.builder()
                .email("testEmail@Email.com")
                .number(123456)
                .verifyNum(123456)
                .build();

        Email email = Email.builder()
                .email(emailDTO.getEmail())
                .number(emailDTO.getNumber())
                .verifyNum(emailDTO.getVerifyNum())
                .build();

        Boolean result = email.getNumber() == email.getVerifyNum();

        when(emailService.mailNumberCheck(emailDTO)).thenReturn(result);

    }
}