package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String date;
    private String customerID;
    private String subCategory;
    private String status;
}
