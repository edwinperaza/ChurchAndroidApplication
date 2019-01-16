package cl.moriahdp.church.registry.modelObject;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

public class UserModelObject extends RealmObject implements Serializable {

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("email")
    @Expose
    @PrimaryKey
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("registration_id")
    @Expose
    private String registrationToken;

    public UserModelObject() {
    }

    public UserModelObject(String firstName, String lastName,
                           String email, String password,
                           String country, String language,
                           String birthday,
                           String token,
                           String registrationToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
        this.token = token;
        this.registrationToken = registrationToken;
    }

    public UserModelObject(String firstName, String lastName,
                           String email,
                           String country, String language,
                           String birthday,
                           String token,
                           String registrationToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.language = language;
        this.birthday = birthday;
        this.token = token;
        this.registrationToken = registrationToken;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getToken() {
        return token;
    }

    public static boolean isUserLoggedIn() {
        Realm realm = Realm.getDefaultInstance();
        UserModelObject userModelObject = realm.where(UserModelObject.class).findFirst();
        UserModelObject user = new UserModelObject();
        if (userModelObject != null) {
            user = realm.copyFromRealm(userModelObject);
        }
        realm.close();
        return (user != null) && (user.getToken() != null);
    }

    public static UserModelObject getUserData() {
        Realm realm = Realm.getDefaultInstance();
        UserModelObject userModelObject = realm.where(UserModelObject.class).findFirst();
        UserModelObject user = new UserModelObject();
        if (userModelObject != null) {
            user = realm.copyFromRealm(userModelObject);
        }
        realm.close();
        return user;
    }

    public static boolean logoutUser() {
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<UserModelObject> userModelObject = realm.where(UserModelObject.class).findAll();
        if (userModelObject != null) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    userModelObject.deleteAllFromRealm();
                }
            });
            realm.close();
            return true;
        }
        realm.close();
        return false;
    }

    public static String getUserLanguage() {
        Realm realm = Realm.getDefaultInstance();
        UserModelObject userModelObject = realm.where(UserModelObject.class).findFirst();
        UserModelObject user = new UserModelObject();
        if (userModelObject != null) {
            user = realm.copyFromRealm(userModelObject);
        }
        realm.close();
        return user.getLanguage();
    }

    public static String getUserToken() {
        Realm realm = Realm.getDefaultInstance();
        UserModelObject userModelObject = realm.where(UserModelObject.class).findFirst();
        UserModelObject user = new UserModelObject();
        if (userModelObject != null) {
            user = realm.copyFromRealm(userModelObject);
        }
        realm.close();
        return "jwt " + user.getToken();
    }

}
