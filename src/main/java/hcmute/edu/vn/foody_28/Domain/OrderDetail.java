package hcmute.edu.vn.foody_28.Domain;

public class OrderDetail
{
    private int OrderID;
    private int FoodID;
    private int Amount;
    private double Price;

    public OrderDetail()
    {

    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID)
    {
        OrderID = orderID;
    }

    public int getFoodID() {
        return FoodID;
    }

    public void setFoodID(int foodID) {
        FoodID = foodID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
