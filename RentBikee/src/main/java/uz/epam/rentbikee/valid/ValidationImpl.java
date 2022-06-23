package uz.epam.rentbikee.valid;

import uz.epam.rentbikee.command.Command;
import uz.epam.rentbikee.command.CommandType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationImpl implements Validation {

    public static final String PHONENUMBER_REGEX = "\\+998[0-9]{9}";
    public static final String FULLNAME_REGEX = "^[A-Za-z]{2,25}$";
    public static final String PASSWORD_REGAX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    public static final String USERNAME_REGAX = "^([A-Za-z]).{2,20}$";


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
        Pattern pattern = Pattern.compile(USERNAME_REGAX);
        Matcher matcher = pattern.matcher(username);
        boolean matches = matcher.matches();
        return matches;
    }


    @Override
    public boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGAX);
        Matcher matcher = pattern.matcher(password);
        boolean matches = matcher.matches();
        return matches;
    }

    @Override
    public boolean validateCommand(String comm) {
        for (CommandType commandType : CommandType.values()
        ) {
            if (commandType.name().equalsIgnoreCase(comm)) {
                return true;
            }
        }
        return false;
    }


    public boolean isUserFieldsValid(String phoneNumber, String name, String userName, String password) {
        return isPasswordValid(password) && isUserNameValid(userName)
                && isNameValid(name) && isPhoneNumberValid(phoneNumber);
    }


}
