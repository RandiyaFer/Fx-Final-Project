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
public class Item {
    @Id
    private String itemCode;
    private String category;
    private String subCategory;
    private String description;
}
