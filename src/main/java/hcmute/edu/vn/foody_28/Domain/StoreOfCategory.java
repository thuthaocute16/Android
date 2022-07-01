package hcmute.edu.vn.foody_28.Domain;

public class StoreOfCategory
{
    private int StoreID;
    private int CategoryID;
    private int Active;

    public StoreOfCategory()
    {

    }
    public int getStoreID()
    {
        return StoreID;
    }

    public void setStoreID(int storeID)
    {
        StoreID = storeID;
    }

    public int getCategoryID()
    {
        return CategoryID;
    }

    public void setCategoryID(int categoryID)
    {
        CategoryID = categoryID;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }
}
