package hcmute.edu.vn.foody_28.Domain;

public class StoreOfStrategy
{
    private int StrategyID;
    private int StoreID;
    private String Date;
    private String RetireDate;
    private int Active;

    public int getStrategyID() {
        return StrategyID;
    }

    public void setStrategyID(int strategyID) {
        StrategyID = strategyID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRetireDate() {
        return RetireDate;
    }

    public void setRetireDate(String retireDate) {
        RetireDate = retireDate;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public StoreOfStrategy()
    {

    }
}
