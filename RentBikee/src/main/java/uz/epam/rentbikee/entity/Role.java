package uz.epam.rentbikee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.epam.rentbikee.entity.type.RoleName;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends AbsEntity{

   private RoleName role;
}
