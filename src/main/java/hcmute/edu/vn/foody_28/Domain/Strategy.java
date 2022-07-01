package hcmute.edu.vn.foody_28.Domain;

public class Strategy
{
    private int StrategyID;
    private String StrategyName;
    private int Active;
    private String Date;

    public Strategy()
    {

    }
    public int getStrategyID()
    {
        return StrategyID;
    }

    public void setStrategyID(int strategyID) {
        StrategyID = strategyID;
    }

    public String getStrategyName() {
        return StrategyName;
    }

    public void setStrategyName(String strategyName) {
        StrategyName = strategyName;
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
