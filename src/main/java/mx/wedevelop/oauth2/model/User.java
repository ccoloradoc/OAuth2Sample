package mx.wedevelop.oauth2.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;

/**
 * Created by colorado on 9/03/17.
 */
@Entity
public class User extends AbstractDomain {
    private String name;
    private String email;
    private String picture;
    private Boolean enabled;

    public User() {}

    public User(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.enabled = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
