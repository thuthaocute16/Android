package hcmute.edu.vn.foody_28.Domain;

public class OrderD
{
    private int OrderID;
    private int UserID;
    private int StoreID;
    private int Active;


    public OrderD(int orderID, int userID, int storeID, int active)
    {
        OrderID = orderID;
        UserID = userID;
        StoreID = storeID;
        Active = active;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getUserID() {
        return UserID;
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

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public OrderD()
    {

    }
    public OrderD(int userID, int storeID, int active)
    {
        UserID = userID;
        StoreID = storeID;
        Active = active;
    }
}
