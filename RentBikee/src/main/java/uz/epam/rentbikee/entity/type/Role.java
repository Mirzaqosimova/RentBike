package uz.epam.rentbikee.entity.type;


public enum Role {
    USER,
    ADMIN;



    public static Role define(String com){
        return Role.valueOf(com.toUpperCase());
    }

}
