package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String CustomerID;
    private String name;
    private String contactNo;
    private String email;
}
