package planIt.planIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import planIt.planIt.domain.Plan;
import planIt.planIt.domain.User;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByUserId(Long Id);

}