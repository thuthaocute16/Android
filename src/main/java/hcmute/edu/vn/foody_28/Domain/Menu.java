package hcmute.edu.vn.foody_28.Domain;

public class Menu
{
    private int MenuID;
    private String MenuName;
    private String MenuImage;
    private int Active;
    private String Date;


    public Menu(String menuName, String menuImage, int active, String date)
    {
        MenuName = menuName;
        MenuImage = menuImage;
        Active = active;
        Date = date;
    }
    public Menu()
    {

    }
    public int getMenuID()
    {
        return MenuID;
    }

    public void setMenuID(int menuID) {
        MenuID = menuID;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public String getMenuImage() {
        return MenuImage;
    }

    public void setMenuImage(String menuImage) {
        MenuImage = menuImage;
    }


    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
