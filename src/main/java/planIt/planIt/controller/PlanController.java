package planIt.planIt.controller;

import com.sun.xml.bind.v2.TODO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import planIt.planIt.common.auth.CustomUserDetails;
import planIt.planIt.controller.dto.PlanDTO;
import planIt.planIt.domain.Plan;
import planIt.planIt.domain.User;
import planIt.planIt.service.PlanService;

import java.util.List;
import java.util.UUID;

@RestController
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping("/main")
    public ResponseEntity<List<Plan>> getUserTodo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getId();
        List<Plan> plans = planService.getPlansByUserId(userId);
        return ResponseEntity.ok(plans);
    }

    @PostMapping("/createPlan")
    public ResponseEntity<Plan> createPlan(@Valid @RequestBody PlanDTO dto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Long userId = customUserDetails.getId();
        Plan plan = planService.createPlan(dto, userId);
        return new ResponseEntity<>(plan, HttpStatus.OK);

    }

}