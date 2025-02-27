package planIt.planIt.service;

import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Null;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import planIt.planIt.controller.dto.EmailDTO;
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