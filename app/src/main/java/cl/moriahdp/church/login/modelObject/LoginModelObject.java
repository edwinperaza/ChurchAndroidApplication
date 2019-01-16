package cl.moriahdp.church.login.modelObject;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModelObject {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("registration_id")
    @Expose
    private String registrationToken;

    public LoginModelObject(String email, String password, String registrationToken) {
        this.email = email;
        this.password = password;
        this.registrationToken = registrationToken;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRegistrationToken() {return registrationToken;}
}
