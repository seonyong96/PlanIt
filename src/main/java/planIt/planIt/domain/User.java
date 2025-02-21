package planIt.planIt.domain;


import jakarta.persistence.*;

import lombok.*;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String verificationToken;

    private String pw;

    private String name;

    private String phoneNumber;

    private String email;

    private String birth;

    private String role;

}
