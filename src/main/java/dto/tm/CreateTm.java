package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateTm extends RecursiveTreeObject<CreateTm> {
    private String email;
    private String password;
    private String confirm;
}
