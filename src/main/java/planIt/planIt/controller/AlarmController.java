package planIt.planIt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import planIt.planIt.common.auth.CustomUserDetails;
import planIt.planIt.service.AlarmService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/send")
    public ResponseEntity<Void> sendAlarm(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long userId = customUserDetails.getId();
        LocalDate date = LocalDate.now();
        alarmService.sendAlarm(userId, date);

        return ResponseEntity.ok().build();

    }

}
