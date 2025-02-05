package planIt.planIt.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Entity
@Builder
//@Table(name="Member")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String userId;
    private String pw;
    private String name;
    private String phoneNumber;
    private String email;
    private String birth;
    private Date createTime;
    private Date modifyTime;
    private String role;

}
