package hcmute.edu.vn.foody_28.Domain;

public class CheckIn
{
    private int CheckInID;
    private int UserID;
    private int StoreID;
    private String Content;
    private String CheckInImage;
    private String Time;
    private String Date;
    private int Active;

    public int getUserID()
    {
        return UserID;
    }

    public void setUserID(int userID)
    {
        UserID = userID;
    }
    public CheckIn()
    {

    }
    public int getCheckInID() {
        return CheckInID;
    }

    public void setCheckInID(int checkInID) {
        CheckInID = checkInID;
    }


    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int storeID) {
        StoreID = storeID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCheckInImage() {
        return CheckInImage;
    }

    public void setCheckInImage(String checkInImage) {
        CheckInImage = checkInImage;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }
}
