package entity;

import com.google.protobuf.StringValue;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Orders {
    @Id
    private String id;
    private String date;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String subCategory;
    private String status;

    public Orders(String orderId, String date,Customer customer, String subCategory, String status) {
        this.id = orderId;
        this.date = date;
        this.customer=customer;
        this.subCategory = subCategory;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", customer=" + customer +
                ", subCategory='" + subCategory + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}