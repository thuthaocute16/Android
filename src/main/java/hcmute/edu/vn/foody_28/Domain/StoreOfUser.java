package hcmute.edu.vn.foody_28.Domain;

public class StoreOfUser
{
    private int UserID;
    private int StoreID;
    private String Background;
    private int Active;

    public int getUserID()
    {
        return UserID;
    }

    public StoreOfUser()
    {

    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getBackGroundStore() {
        return Background;
    }

    public void setBackGroundStore(String Background) {
        this.Background = Background;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }
}
