package uz.epam.rentbikee.entity;

import lombok.*;
import uz.epam.rentbikee.entity.type.BikeStatus;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bike extends AbsEntity{

      private String color;

   private  int  size;

   private int seatsNum;


private BikeStatus bikeStatus;

      private Price price;

     private Address address;




}
