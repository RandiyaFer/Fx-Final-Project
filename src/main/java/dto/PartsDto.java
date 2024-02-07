package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PartsDto {
    private String id;
    private String date;
    private String customerId;
    private String subCategory;
    private String status;
    private String part;
    private int qty;
    private Double total;

    public PartsDto(String id, String date, String subCategory, String status,String part,int qty,Double total) {
        this.id=id;
        this.date=date;
        this.subCategory=subCategory;
        this.status=status;
        this.part=part;
        this.qty=qty;
        this.total=total;
    }
}
