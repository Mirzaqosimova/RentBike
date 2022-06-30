package uz.epam.rentbikee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.epam.rentbikee.entity.type.RoleName;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends AbstractEntity {

   private RoleName role;

   public Role(Long id, RoleName role) {
      super(id);
      this.role = role;
   }
}
