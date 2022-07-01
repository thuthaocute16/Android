package hcmute.edu.vn.foody_28.Domain;

public class FoodOfStore
{
    private int FoodID;
    private int StoreID;
    private int DivideFoodID;
    private int Active;
    private String FoodImage;
    private double PriceRoot;
    private double PriceSale;
    private int IsSale;
    private String FoodName;
    private String Des;

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getFoodID()
    {
        return FoodID;
    }

    public void setFoodID(int foodID)
    {
        FoodID = foodID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public int getDivideFoodID() {
        return DivideFoodID;
    }

    public void setDivideFoodID(int divideFoodID) {
        DivideFoodID = divideFoodID;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public String getFoodImage() {
        return FoodImage;
    }

    public void setFoodImage(String foodImage) {
        FoodImage = foodImage;
    }

    public double getPriceRoot() {
        return PriceRoot;
    }

    public void setPriceRoot(double priceRoot) {
        PriceRoot = priceRoot;
    }

    public double getPriceSale() {
        return PriceSale;
    }

    public void setPriceSale(double priceSale) {
        PriceSale = priceSale;
    }

    public int getIsSale() {
        return IsSale;
    }

    public void setIsSale(int isSale) {
        IsSale = isSale;
    }

    public FoodOfStore()
    {

    }

}
