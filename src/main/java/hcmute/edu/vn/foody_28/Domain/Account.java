package hcmute.edu.vn.foody_28.Domain;

public class Account
{
    private int AccountID;
    private String Username;
    private String Password;
    private String Role;
    private int Active;
    private String Date;

    public Account(int accountID, String username, String password, String role, int active, String date)
    {
        AccountID = accountID;
        Username = username;
        Password = password;
        Role = role;
        Active = active;
        Date = date;
    }

    public Account()
    {

    }
    public Account(String username, String password, String role, int active, String date)
    {
        Username = username;
        Password = password;
        Role = role;
        Active = active;
        Date = date;
    }


    public int getAccountID() {
        return AccountID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public void setAccountID(int accountID) {
        AccountID = accountID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRole(String role) {
        Role = role;
    }

    public void setActive(int active) {
        Active = active;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getActive() {
        return Active;
    }

    public String getDate() {
        return Date;
    }
}
