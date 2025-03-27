package planIt.planIt.service;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import planIt.planIt.controller.dto.etc.EmailDTO;
import planIt.planIt.domain.Email;
import planIt.planIt.repository.EmailRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

//    @BeforeEach()
//    public void SetUp() {
//        MimeMessage mimeMessage = new MimeMessage((Session) null);
//        mailSender = mock(JavaMailSender.class);
//        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
//        emailService = new EmailService(mailSender);
//    }

    @Test
    void sendMailTest() {

        //given
        EmailDTO emailDTO = EmailDTO.builder()
                .email("testEmail@Email.com")
                .number(123456)
                .build();

        Email email = Email.builder()
                .email(emailDTO.getEmail())
                .number(emailDTO.getNumber())
                .build();

        when(emailRepository.save(any())).thenReturn(email);
        doNothing().when(mailSender).send(any(MimeMessage.class));


        //when
        Email saveEmail = emailService.sendMail(emailDTO);

        //then
        assertNotNull(saveEmail);
        assertEquals(123456, saveEmail.getNumber());
        verify(emailRepository, times(1)).save(any(Email.class));
    }

    @Test
    void mailNumberCheckTest() {

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

        when(emailRepository.findByEmail(any())).thenReturn(email);

        //when
        boolean result = emailService.mailNumberCheck(emailDTO);

        //then
        assertTrue(result);

    }
}