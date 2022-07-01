package hcmute.edu.vn.foody_28.Domain;

import java.util.List;

public class Category
{
    private int CategoryID;
    private String CategoryName;
    private int CategoryRoot;
    private String CategoryImage;
    private String Date;
    private int Active;

    public int getActive()
    {
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

    public int getCategoryID()
    {
        return CategoryID;
    }

    public void setCategoryID(int categoryID)
    {
        CategoryID = categoryID;
    }

    public String getCategoryName()
    {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getCategoryRoot() {
        return CategoryRoot;
    }

    public void setCategoryRoot(int categoryRoot) {
        CategoryRoot = categoryRoot;
    }

    public String getCategoryImage() {
        return CategoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        CategoryImage = categoryImage;
    }

    public Category()
    {

    }

}
