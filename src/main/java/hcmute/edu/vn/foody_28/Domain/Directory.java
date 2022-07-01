package hcmute.edu.vn.foody_28.Domain;

public class Directory
{
    private int resource_ID;
    private String title;

    public Directory(int resource_ID, String title)
    {
        this.resource_ID = resource_ID;
        this.title = title;
    }

    public int getResource_ID() {
        return resource_ID;
    }

    public String getTitle() {
        return title;
    }

    public void setResource_ID(int resource_ID) {
        this.resource_ID = resource_ID;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
