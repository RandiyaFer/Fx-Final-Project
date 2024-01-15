package dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemCode;
    private String category;
    private String subCategory;
    private String description;
}
