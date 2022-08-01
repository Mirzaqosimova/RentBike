package uz.epam.rentbikee.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Detail extends AbstractEntity {

    private String name;


}
