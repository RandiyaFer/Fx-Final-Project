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
    private double Advance;
    private String itemCode;
    private String userId;
}
