package uz.epam.rentbikee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AbstractEntity {

    private double lant;

    private double longt;

    private String description;

    private String city;

    private String street;


}
