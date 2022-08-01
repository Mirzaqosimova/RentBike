package uz.epam.rentbikee.entity;

import lombok.*;
import uz.epam.rentbikee.entity.type.BikeStatus;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bike extends AbstractEntity {

    private String brandName;

    private String description;

    private int size;

    private BikeStatus bikeStatus;

    private Price price;

    private Address address;

    private boolean isDelete;

    private String fileName;

    private CategoryBike bikeCategory;


}
