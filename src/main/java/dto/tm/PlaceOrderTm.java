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
    private String OrderId;
    private String customerID;
    private String category;
    private String itemCode;
    private String date;
    private String status;
    private String details;
    private Button btn;

}
