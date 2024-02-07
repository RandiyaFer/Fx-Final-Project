package dto.tm;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class partsTm extends RecursiveTreeObject<partsTm> {
    private String orderId;
    private String date;
    private String customerId;
    private String subCategory;
    private String status;
    private String part;
    private int qty;
    private Double total;
}
