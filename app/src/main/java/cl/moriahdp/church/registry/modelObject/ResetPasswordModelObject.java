package cl.moriahdp.church.registry.modelObject;

import java.io.Serializable;

public class ResetPasswordModelObject implements Serializable {

    private String email;

    public ResetPasswordModelObject(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
