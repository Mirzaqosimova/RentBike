package uz.epam.rentbikee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.epam.rentbikee.entity.type.OrderStatus;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order  extends AbstractEntity {



    private User customer;

    private Bike bike;


    private Timestamp orderTime;

  private OrderStatus orderStatus;

    private int driveTime;

    private double priceSum;

}
