package config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatedUserModel {
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String gender;
    private String address;
//    public UpdatedUserModel(String firstname, String lastname, String email, String phoneNumber, String gender, String address){
//        this.firstname=firstname;
//        this.lastname=lastname;
//        this.email=email;
//        this.phoneNumber=phoneNumber;
//        this.gender=gender;
//        this.address=address;
//
//    }
    public UpdatedUserModel(){

    }
}
