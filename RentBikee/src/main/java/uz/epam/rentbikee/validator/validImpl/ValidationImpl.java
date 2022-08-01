package uz.epam.rentbikee.validator.validImpl;

import uz.epam.rentbikee.command.CommandType;
import uz.epam.rentbikee.validator.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

    public static final String PHONENUMBER_REGEX = "\\+998[0-9]{9}";
    public static final String FULLNAME_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    public static final String ID_REGEX = "^[0-9]+$";


    @Override
    public boolean isPhoneNumberValid(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONENUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public boolean isNameValid(String name) {
        Pattern pattern = Pattern.compile(FULLNAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        boolean matches = matcher.matches();
        return matches;
    }

    @Override
    public boolean isUserNameValid(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        boolean matches = matcher.matches();
        return matches;
    }


    @Override
    public boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        boolean matches = matcher.matches();
        return matches;
    }

    @Override
    public boolean validateCommand(String command) {
        for (CommandType commandType : CommandType.values()
        ) {
            if (commandType.name().equalsIgnoreCase(command)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateId(String id) {
        Pattern pattern = Pattern.compile(ID_REGEX);
        Matcher matcher = pattern.matcher(id);
        boolean matches = matcher.matches();
        return matches;
    }



    public boolean isUserFieldsValid(String phoneNumber, String name, String userName, String password) {
        return isPasswordValid(password) && isUserNameValid(userName)
                && isNameValid(name) && isPhoneNumberValid(phoneNumber);
    }


}
