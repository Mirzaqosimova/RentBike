package uz.epam.rentbikee.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.epam.rentbikee.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String username;

    private String fullname;

    private String phoneNumber;

    private boolean isBlock;

    private String roleName;

    private double balance;

}
