package planIt.planIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import planIt.planIt.common.enums.ErrorCode;
import planIt.planIt.common.exeption.CustomException;
import planIt.planIt.controller.dto.plan.PlanDTO;
import planIt.planIt.controller.dto.plan.UpdatePlanDTO;
import planIt.planIt.domain.Plan;
import planIt.planIt.domain.User;
import planIt.planIt.repository.PlanRepository;
import planIt.planIt.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    /**
     * Plan 전체 조회
     *
     * @param userId
     * @return List<Plan>
     */
    public List<Plan> getPlansByUserId(Long userId) {

//        User user = userRepository.findByUserId(userId)
//                .orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_ID);

        return planRepository.findByUserId(userId);
    }

    /**
     * 신규 Plan 생성
     *
     * @param dto
     * @param userId
     * @return Plan
     */
    public Plan createPlan(PlanDTO dto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTFOUND_USER));

        Plan plan = Plan.builder()
                .description(dto.getDescription())
                .date(dto.getDate())
                .user(user)
                .build();

        planRepository.save(plan);

        return plan;
    }

    /** Plan 수정
     *
     *
     * @param dto
     * @param planId
     * @return boolean
     */
    public boolean updatePlan(UpdatePlanDTO dto, Long planId) {

        Optional<Plan> optionalPlan = planRepository.findById(planId);

        Plan plan = optionalPlan.orElseThrow(() ->
                new CustomException(ErrorCode.NOTFOUND_PLAN)
        );

        plan.setDescription(dto.getUpdateDescription());
        planRepository.save(plan);

        return true;
    }

    /** Plan 삭제
     * TODO description의 세부사항 ex) 1번 2번 3번 만 삭제할 수 있도록 구현하기.
     * @param planId
     * @return boolean
     */
    public boolean deletePlan(Long planId) {

        Optional<Plan> optionalPlan = planRepository.findById(planId);

        Plan plan = optionalPlan.orElseThrow(() ->
                new CustomException(ErrorCode.NOTFOUND_PLAN)
        );

        planRepository.delete(plan);

        return true;
    }

    public List<Plan> getPlanByDate(Long userId, LocalDate date) {

        return planRepository.findByUserIdAndDate(userId, date);

    }

    public List<Plan> getPlansByMonth(Long userId, int year, int month) {

        return planRepository.findByUserIdAndMonth(userId, year, month);

    }

}