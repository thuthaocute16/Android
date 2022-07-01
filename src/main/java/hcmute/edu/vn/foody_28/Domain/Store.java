package hcmute.edu.vn.foody_28.Domain;

import java.io.Serializable;

public class Store implements Serializable
{
    private int StoreID;
    private String StoreName;
    private String StoreImage;
    private String Date;
    private String TimeOn;
    private String TimeOff;
    private String Address;
    private String Owner;
    private int AmountDivideFood;
    private double Rate;

    public int getAmountDivideFood()
    {
        return AmountDivideFood;
    }

    public void setAmountDivideFood(int amountDivideFood)
    {
        AmountDivideFood = amountDivideFood;
    }

    public double getRate()
    {
        return Rate;
    }
    public void setRate(double rate)
    {
        Rate = rate;
    }
    private int Active;
    private int CategoryID;
    private int VoucherID;
    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    private int StrategyID;

    public Store()
    {

    }
    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getStoreImage() {
        return StoreImage;
    }

    public void setStoreImage(String storeImage) {
        StoreImage = storeImage;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTimeOn() {
        return TimeOn;
    }

    public void setTimeOn(String timeOn) {
        TimeOn = timeOn;
    }

    public String getTimeOff() {
        return TimeOff;
    }

    public void setTimeOff(String timeOff) {
        TimeOff = timeOff;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int voucherID) {
        VoucherID = voucherID;
    }

    public int getStrategyID() {
        return StrategyID;
    }

    public void setStrategyID(int strategyID) {
        StrategyID = strategyID;
    }
}
