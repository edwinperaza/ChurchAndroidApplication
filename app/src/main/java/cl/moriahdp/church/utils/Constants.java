package cl.moriahdp.church.utils;


import java.util.regex.Pattern;

public class Constants {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
    public static final int PASSWORD_MIN = 4;
    public static final String FIREBASE_TOKEN = "firebase_token";
    public static final String FROM_PROFILE_CHANGE_LANGUAGE = "from_profile_change_language";
    public static final String APP_NAME = "CHURCH_APP";

}
