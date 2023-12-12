package Utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private final static String REGEX_NAME = "^[A-Z][A-Za-z ]{2,18}$";
    private final static String REGEX_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{6,20}$";
    private final static String REGEX_EMAIL = "^[A-Za-z][a-zA-Z0-9_]{4,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    private final static String REGEX_ADDRESS = "^[A-Za-z0-9 ]{2,}$";
    private final static String REGEX_PHONENUMBER = "^0[0-9]{9}$";
    public static boolean isValidName(String name){
        return Pattern.matches(REGEX_NAME, name);
    }
    public static boolean isValidPassword(String password){
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    public static boolean isValidEmail(String email){
        return Pattern.matches(REGEX_EMAIL, email);
    }
    public static boolean isValidAddress(String address){
        return Pattern.matches(REGEX_ADDRESS, address);
    }
    public static boolean isValidPhoneNumber(String phoneNumber){
        return Pattern.matches(REGEX_PHONENUMBER, phoneNumber);
    }

    private final static String REGEX_SHOWNAME = "^[A-Za-z0-9]";
    public static boolean isValidShowName(String showName){
        return Pattern.matches(REGEX_SHOWNAME, showName);
    }
    private final static String REGEX_SINGER = "^[A-Z][A-Za-z ]";

    public static boolean isValidSinger(String singer){
        return Pattern.matches(REGEX_SINGER, singer);
    }
    public final static String REGEX_SHOWPRICE = "^\\d+$";
    public static boolean isValidShowPrice(String showPrice){
        return Pattern.matches(REGEX_SHOWPRICE, showPrice);
    }
}
