package entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Parts {
        @Id
        private String id;
        private String date;

        @ManyToOne
        @JoinColumn(name = "customerID")
        private Customer customer;

        private String subCategory;
        private String status;
        private String part;
        private int qty;
        private Double total;

        @Override
        public String toString() {
            return "Orders{" +
                    "id=" + id +
                    ", date='" + date + '\'' +
                    ", customer=" + customer +
                    ", subCategory='" + subCategory + '\'' +
                    ", status='" + status + '\'' +
                    ", part='"+ part + '\'' +
                    ", total='"+ total + '\'' +
                    '}';
        }

    }
