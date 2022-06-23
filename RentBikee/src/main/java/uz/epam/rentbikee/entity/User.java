package uz.epam.rentbikee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AbsEntity {

    private String username;

    private String fullname;

    private String password;

    private String phoneNumber;

    private boolean isBlock;

    private Role role;

    private double balance;

}
