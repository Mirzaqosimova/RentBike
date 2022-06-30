package uz.epam.rentbikee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price extends AbstractEntity {


    private int minut;



    private double price;


}
