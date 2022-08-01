package uz.epam.rentbikee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BikeDetailsBike {

    private Detail bikeDetail;

    private Bike bike;

}
