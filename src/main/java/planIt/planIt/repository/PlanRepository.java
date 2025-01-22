package planIt.planIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import planIt.planIt.domain.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {


}