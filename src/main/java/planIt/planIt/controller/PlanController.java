package planIt.planIt.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import planIt.planIt.common.auth.CustomUserDetails;
import planIt.planIt.controller.dto.PlanDTO;
import planIt.planIt.controller.dto.UpdatePlanDTO;
import planIt.planIt.domain.Plan;
import planIt.planIt.service.PlanService;

import java.util.List;

@RestController
public class PlanController {

    private final PlanService planService;

    @Autowired
    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    /**
     * TodoList 조회
     *
     * @param customUserDetails
     * @return Plan
     */
    @GetMapping("/main")
    public ResponseEntity<List<Plan>> getUserTodo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long userId = customUserDetails.getId();
        List<Plan> plans = planService.getPlansByUserId(userId);

        return ResponseEntity.ok(plans);
    }

    /**
     * 신규 TodoList 등록
     *
     * @param dto
     * @param customUserDetails
     * @return Plan
     */
    @PostMapping("/createPlan")
    public ResponseEntity<Plan> createPlan(@Valid @RequestBody PlanDTO dto,
                                           @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        Long userId = customUserDetails.getId();
        Plan plan = planService.createPlan(dto, userId);

        return new ResponseEntity<>(plan, HttpStatus.OK);

    }

    /**
     * TodoList 업데이트
     *
     * @param dto
     * @param customUserDetails
     * @return boolean
     */
    @PostMapping("/updatePlan/{planId}")
    public ResponseEntity<?> createPlan(@Valid @RequestBody UpdatePlanDTO dto,
                                        @PathVariable Long planId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        boolean updatePlan = planService.updatePlan(dto, planId);
        return new ResponseEntity<>(updatePlan, HttpStatus.OK);
    }

    /**
     * TodoList 삭제
     *
     * @param dto
     * @param planId
     * @param customUserDetails
     * @return boolean
     */
    @PostMapping("/deletePlan/{planId}")
    public ResponseEntity<?> deletePlan(@Valid @RequestBody UpdatePlanDTO dto,
                                        @PathVariable Long planId,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        boolean deletePlan = planService.deletePlan(planId);

        return new ResponseEntity<>(deletePlan, HttpStatus.OK);
    }

}