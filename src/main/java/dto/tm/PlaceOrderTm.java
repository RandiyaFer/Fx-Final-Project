package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderTm extends RecursiveTreeObject<PlaceOrderTm> {
    private String itemCode;
    private String subCategory;
    private String description;
    private double Advance;
    private Button btn;
}
