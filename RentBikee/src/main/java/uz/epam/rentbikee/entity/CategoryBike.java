package uz.epam.rentbikee.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryBike extends AbstractEntity{

    private String name;

}
