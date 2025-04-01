package planIt.planIt.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import planIt.planIt.domain.Plan;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class AlarmService {

    private final SimpMessagingTemplate messagingTemplate;
    private final PlanService planService;

    public AlarmService(SimpMessagingTemplate messagingTemplate, PlanService planService) {
        this.messagingTemplate = messagingTemplate;
        this.planService = planService;
    }

    public void sendAlarm(Long userId, LocalDate date) {

        List<Plan> todo = planService.getPlanByDate(userId, date);

        //WebSocket 테스트용
        System.out.println("*********알람테스트********* userId : " + userId + " date : " + date);
        System.out.println("*********알람테스트********* todo : " + todo);

        messagingTemplate.convertAndSend("/topic/alarm" + userId, todo != null? todo : Collections.emptyList());

    }
}
