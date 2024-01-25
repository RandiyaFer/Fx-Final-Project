package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class placeOrder {
    @Id
    private String OrderId;
    private String customerID;
    private String itemCode;
    private String userId;
    private double Advance;
}
