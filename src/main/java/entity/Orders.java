package entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    private String orderId;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String subCategory;
    private String status;

    public Orders(String orderId, String date, String subCategory, String status) {
        this.orderId = orderId;
        this.date = date;
        this.subCategory = subCategory;
        this.status = status;
    }
}