package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderTm extends RecursiveTreeObject<OrderTm> {
    private String orderId;
    private String date;
    private String customerId;
    private String subCategory;
    private String status;
    private Button btn;
}
