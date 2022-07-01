package hcmute.edu.vn.foody_28.Domain;

public class User
{
    private int UserID;
    private String Username;
    private String FacebookID;
    private String Name;
    private String Image;
    private String Address;
    private String Description;
    private String ZaloID;
    private String Phone;
    private String GoogleID;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    private String Date;
    private int Active;

    public String getZaloID() {
        return ZaloID;
    }

    public void setZaloID(String zaloID) {
        ZaloID = zaloID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGoogleID() {
        return GoogleID;
    }

    public void setGoogleID(String googleID) {
        GoogleID = googleID;
    }

    public User()
    {

    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFacebookID() {
        return FacebookID;
    }

    public void setFacebookID(String facebookID) {
        FacebookID = facebookID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
