package hcmute.edu.vn.foody_28.Domain;

public class Facebook
{
    private String FacebookID;
    private String FirstName;
    private String LastName;
    private String Email;
    private String ImageURL;

    public Facebook(String facebookID, String firstName, String lastName, String email, String imageURL) {
        FacebookID = facebookID;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        ImageURL = imageURL;
    }

    public String getFacebookID()
    {
        return FacebookID;
    }
    public void setFacebookID(String facebookID)
    {
        FacebookID = facebookID;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}

