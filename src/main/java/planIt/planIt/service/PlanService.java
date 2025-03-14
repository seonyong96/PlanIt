package planIt.planIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planIt.planIt.common.auth.CustomUserDetails;
import planIt.planIt.common.enums.ErrorCode;
import planIt.planIt.common.exeption.CustomException;
import planIt.planIt.controller.dto.PlanDTO;
import planIt.planIt.domain.Plan;
import planIt.planIt.domain.User;
import planIt.planIt.repository.PlanRepository;
import planIt.planIt.repository.UserRepository;

import java.util.List;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public List<Plan> getPlansByUserId(Long userId) {

//        User user = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_ID);

        return planRepository.findByUserId(userId);
    }

    public Plan createPlan(PlanDTO dto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow( () -> new CustomException(ErrorCode.NOTFOUND_USER));

        Plan plan = Plan.builder()
                .description(dto.getDescription())
                .target_date(dto.getTarget_date())
                .user(user)
                .build();

        planRepository.save(plan);
        return plan;
    }



}