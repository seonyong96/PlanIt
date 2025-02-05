package planIt.planIt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date modifyTime;

}
