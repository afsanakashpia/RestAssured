package PageRunner;

import com.github.javafaker.Faker;
import config.ItemModel;
import config.UserModel;
import config.setup;
import config.UpdatedUserModel;
import controller.userController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class TestRunner extends setup {
    private Properties prop;
    private userController userController;
    private final Faker faker = new Faker();
    private final UserModel userModel = new UserModel();
    private final UpdatedUserModel updatedUserModel = new UpdatedUserModel();
    private final ItemModel itemModel = new ItemModel();
    String password = "1234";

    @BeforeMethod
    public void init() throws IOException {
        prop = new Properties();
        FileInputStream fs = new FileInputStream("src/test/resources/config.properties");
        prop.load(fs);
        userController = new userController(prop);
    }

    @Test(priority = 1)
    public void userRegistration() throws ConfigurationException {

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail("user" + Utils.generateRandomNum(100, 999) + "@gmail.com");
        String password = "1234";
        userModel.setPassword(password);
        String phoneNumber = "017" + Utils.generateRandomNum(10000000, 99999999);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setGender("Male");
        String address = faker.address().fullAddress();
        userModel.setAddress(address);
        userModel.setTermsAccepted(true);
        Response res = userController.userRegistration(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String userEmail = jsonObj.getString("email");
        String userId = jsonObj.getString("_id");

        Utils.setEnvVar("userPassword", password);
        Utils.setEnvVar("userEmail", userEmail);
        Utils.setEnvVar("userId", userId);
        Utils.setEnvVar("userAddress", address);
        Utils.setEnvVar("phoneNumber", phoneNumber);

    }

    @Test(description = "UserRegistration With existing Email")
    public void UserRegistrationInvalid() {
        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword("1234");
        String phoneNumber = "017" + Utils.generateRandomNum(10000000, 99999999);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setGender("Male");
        String address = faker.address().fullAddress();
        userModel.setAddress(address);
        userModel.setTermsAccepted(true);
        Response res = userController.UserRegistrationWithexistingEmail(userModel);
        Assert.assertEquals(res.getStatusCode(), 400);
        System.out.println(res.asString());

    }

    @Test(priority = 2)
    public void AdminLogin() throws ConfigurationException {
        userModel.setEmail(System.getProperty("email"));
        userModel.setPassword(System.getProperty("password"));
        Response res = userController.Adminlogin(userModel);
        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String token = jsonObj.getString("token");
        Utils.setEnvVar("adminToken", token);

    }

    // @Test(priority = 3)
    public void userlist() throws IOException {
        Response res = userController.Userlist();
        System.out.println(res.asString());
    }

    @Test(priority = 4)
    public void searchUser() {
        Response res = userController.searchUser(prop.getProperty("userId"));
        System.out.println(res.asString());
    }


    @Test(priority = 5)
    public void UpdateUser() throws ConfigurationException {
        updatedUserModel.setFirstname(faker.name().firstName());
        updatedUserModel.setLastname(faker.name().lastName());
        updatedUserModel.setEmail(prop.getProperty("userEmail"));
        updatedUserModel.setPhoneNumber(prop.getProperty("phoneNumber"));
        updatedUserModel.setGender("Male");
        updatedUserModel.setAddress(prop.getProperty("userAddress"));
        Response res = userController.updateUser(updatedUserModel, prop.getProperty("userId"));
        System.out.println(res.asString());
    }

    @Test(priority = 6)
    public void Userlogin() throws ConfigurationException, IOException {
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword(prop.getProperty("userPassword"));
        Response res = userController.userlogin(userModel);
        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        Utils.setEnvVar("UserToken", jsonObj.getString("token"));
    }
@Test(description = "User login with invalid password")
    public void userLoginInvalid() {
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword("123");
        Response res = userController.userLoginInvalid(userModel);
        Assert.assertEquals(res.getStatusCode(), 401);
    }


    @Test(priority = 7)
    public void ItemList() throws IOException {
        Response res = userController.Itemlist(prop.getProperty("UserToken"));
        System.out.println(res.asString());
    }


    @Test(priority = 8)
    public void AddItem() throws ConfigurationException {
        itemModel.setItemName(faker.food().fruit());
        itemModel.setAmount(String.valueOf(faker.number().randomDigitNotZero()));
        itemModel.setQuantity(2);
        itemModel.setMonth(LocalDate.now().getMonth().toString());
        itemModel.setPurchaseDate(LocalDate.now().plusDays(1).toString());
        itemModel.setRemarks(faker.lorem().sentence());
        Response res = userController.AddItem(itemModel, prop.getProperty("UserToken"));
        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String itemId = jsonObj.getString("_id");
        int quantity = jsonObj.getInt("quantity"); // Get quantity as an integer
        String amount = jsonObj.getString("amount");
        String purchaseDate = jsonObj.getString("purchaseDate");
        String month = jsonObj.getString("month");
        String remarks = jsonObj.getString("remarks");
        Utils.setEnvVar("itemId", itemId);
        Utils.setEnvVar("quantity", String.valueOf(quantity)); // Convert integer to string
        Utils.setEnvVar("amount", amount);
        Utils.setEnvVar("purchaseDate", purchaseDate);
        Utils.setEnvVar("month", month);
        Utils.setEnvVar("remarks", remarks);
    }

    @Test(priority = 9)
    public void EditName() throws ConfigurationException {
        itemModel.setItemName(faker.food().fruit());
        itemModel.setAmount(prop.getProperty("amount"));
        itemModel.setQuantity(Integer.parseInt(prop.getProperty("quantity")));
        itemModel.setMonth(prop.getProperty("month"));
        itemModel.setPurchaseDate(prop.getProperty("purchaseDate"));
        itemModel.setRemarks(prop.getProperty("remarks"));
        String userId = prop.getProperty("userId");
        String itemId = prop.getProperty("itemId");
        String userToken = prop.getProperty("UserToken");
        Response res = userController.editItem(itemModel, userId, itemId, userToken);
        System.out.println(res.asString());
        JsonPath jsonObj = res.jsonPath();
        String itemName = jsonObj.getString("itemName");
        Utils.setEnvVar("itemName", itemName);

    }
@Test(description = "delete Item with wrong id")
    public void deleteItemInvalid() {
        Response res = userController.deleteItemInvalid(prop.getProperty("UserToken"),"wrongId");
        System.out.println(res.asString());
    }

    @Test(priority = 10)
    public void deleteItem() {
        Response res = userController.deleteItem(prop.getProperty("UserToken"), prop.getProperty("itemId"));
        System.out.println(res.asString());
    }


}
