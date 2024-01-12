package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Register {
    private String name;
    @Id
    private String email;
    private String jobRole;
    private String contNo;
}
