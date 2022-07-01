package hcmute.edu.vn.foody_28.Domain;

import android.graphics.Bitmap;

public class Banner
{
    private int BannerID;
    private String Name;
    private String BannerLink;
    private String Active;

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public int getBannerID() {
        return BannerID;
    }

    public void setBannerID(int bannerID) {
        BannerID = bannerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Banner(int bannerID, String name, String bannerLink) {
        BannerID = bannerID;
        Name = name;
        BannerLink = bannerLink;
    }

    public String getBannerLink()
    {
        return BannerLink;
    }

    public void setBannerLink(String bannerLink)
    {
        BannerLink = bannerLink;
    }

    public Banner()
    {
    }
}
