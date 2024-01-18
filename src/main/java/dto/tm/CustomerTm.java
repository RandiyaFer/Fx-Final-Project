package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTm extends RecursiveTreeObject<CustomerTm> {
    private String CustomerID;
    private String name;
    private String contactNo;
    private String email;
    private Button btn;
}
