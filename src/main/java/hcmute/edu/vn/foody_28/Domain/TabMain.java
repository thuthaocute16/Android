package hcmute.edu.vn.foody_28.Domain;

public class TabMain
{
    private int TabMainID;
    private String TabMainName;
    private int Active;

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public TabMain()
    {

    }
    public int getTabMainID() {
        return TabMainID;
    }

    public void setTabMainID(int tabMainID) {
        TabMainID = tabMainID;
    }

    public String getTabMainName() {
        return TabMainName;
    }

    public void setTabMainName(String tabMainName) {
        TabMainName = tabMainName;
    }
}
