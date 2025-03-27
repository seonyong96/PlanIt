package planIt.planIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import planIt.planIt.domain.Plan;
import planIt.planIt.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByUserId(Long Id);
    List<Plan> findByUserIdAndDate(Long Id, LocalDate date);

    @Query("SELECT p FROM Plan p WHERE p.user.id = :Id AND FUNCTION('YEAR', p.date) = :year AND FUNCTION('MONTH', p.date) = :month")
    List<Plan> findByUserIdAndMonth(@Param("Id") Long Id, @Param("year") int year, @Param("month") int month);
}