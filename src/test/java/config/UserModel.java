package config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String gender;
    private boolean termsAccepted;

    public UserModel(){

    }
}

