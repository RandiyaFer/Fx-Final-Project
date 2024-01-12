package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTm extends RecursiveTreeObject<RegisterTm> {
    private String name;
    private String email;
    private String jobRole;
    private String conNo;
}
