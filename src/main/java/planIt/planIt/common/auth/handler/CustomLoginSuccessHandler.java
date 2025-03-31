package planIt.planIt.common.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import planIt.planIt.common.auth.CustomUserDetails;
import planIt.planIt.domain.Plan;
import planIt.planIt.service.AlarmService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private final SimpMessagingTemplate messagingTemplate;
    private final AlarmService alarmService;

    public CustomLoginSuccessHandler(SimpMessagingTemplate messagingTemplate, AlarmService alarmService) {
        this.messagingTemplate = messagingTemplate;
        this.alarmService = alarmService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)  throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        LocalDate date = LocalDate.now();

        alarmService.sendAlarm(customUserDetails.getId(), date);
    }


}
