package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String id;
    private String date;
    private String customerId;
    private String subCategory;
    private String status;

    public OrderDto(String id, String date, String subCategory, String status) {
        this.id=id;
        this.date=date;
        this.subCategory=subCategory;
        this.status=status;
    }
}
