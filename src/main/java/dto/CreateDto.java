package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateDto {
    private String email;
    private String password;
    private String confirm;
}
