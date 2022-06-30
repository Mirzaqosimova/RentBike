package uz.epam.rentbikee.validator;

public interface Validation {

    boolean isPhoneNumberValid(String phoneNumber);

    boolean isNameValid(String name);

    boolean isUserNameValid(String username);

    boolean isPasswordValid(String password);

    boolean validateCommand(String command);

    boolean validateId(String id);
}
