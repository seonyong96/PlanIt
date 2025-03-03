package planIt.planIt.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import planIt.planIt.controller.dto.EmailDTO;
import planIt.planIt.domain.Email;
import planIt.planIt.repository.EmailRepository;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Autowired
    private JavaMailSender mailSender;

    private final String senderMail = "csy21977@gmail.com"; // 발신 이메일 주소
//    private int number;

    /** 100000 ~ 999999 사이의 난수 생성
     *
     */
    public int createNumber() {
        return (int)(Math.random() * 900000) + 100000;
    }


    /** 이메일 생성
     *
     * @param mail 발신자 이메일
     * @param emailNumber 이메일 발소된 인증번호
     * @return
     */
    public MimeMessage createMail(String mail, int emailNumber) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(senderMail); // 발신자 이메일 설정
            message.setRecipients(MimeMessage.RecipientType.TO, mail); // 수신자 이메일 설정
            message.setSubject("이메일 인증"); // 이메일 제목 설정
            // 이메일 본문
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + emailNumber + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message; // 생성된 이메일 메시지 객체 반환
    }

    /** eamilRepository 저장 및 이메일 발송
     *
     * @param dto
     * @return Email
     */
    public Email sendMail(EmailDTO dto){

        int number = createNumber();

        Email email = Email.builder()
                .email(dto.getEmail())
                .number(number)
                .build();

        MimeMessage message = createMail(email.getEmail(), number);
        mailSender.send(message);
        return emailRepository.save(email);
    }

    /** 이메일 인증번호 검증
     *
     * @param dto
     * @return boolean
     */
    public boolean mailNumberCheck(EmailDTO dto){

        return emailRepository.findByEmail(dto.getEmail()).getNumber() == dto.getVerifyNum();

    }
}
