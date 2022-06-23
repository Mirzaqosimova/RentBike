package uz.epam.rentbikee.entity.type;


public enum RoleName {
    USER,
    ADMIN;



    public static RoleName define(String com){
        return RoleName.valueOf(com.toUpperCase());
    }

}
