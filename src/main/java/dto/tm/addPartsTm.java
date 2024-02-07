package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class addPartsTm extends RecursiveTreeObject<addPartsTm> {
    private String name;
    private Double price;

}
