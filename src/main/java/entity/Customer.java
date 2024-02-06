package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private String CustomerID;
    private String name;
    private String contactNo;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();
    @OneToMany(mappedBy = "customer")
    private List<Parts> parts = new ArrayList<>();

    public Customer(String customerID, String name, String contactNo, String email) {
        this.CustomerID=customerID;
        this.name=name;
        this.contactNo=contactNo;
        this.email=email;
    }
}
