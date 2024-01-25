package dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class placeOrdDto {
    private String OrderId;
    private String customerID;
    private String itemCode;
    private String userId;
    private double Advance;
    //private List<OrderDetailDto> list;
}
