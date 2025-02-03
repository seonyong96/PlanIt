package planIt.planIt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plan_id;
    private Long user_id;
    private String title;
    private String description;
    private Date start_time;
    private Date end_time;
    private Date create_time;
    private Date modify_time;

}
