package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.Button;
import lombok.*;

import java.awt.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemTm extends RecursiveTreeObject<ItemTm> {
    private String itemCode;
    private String category;
    private String subCategory;
    private String description;
    private Button btn;



//    public ItemTm(String itemCode, String category, String subCategory, String description, javafx.scene.control.Button btn) {
//    }
}
