package hcmute.edu.vn.foody_28.Domain;

public class Driver
{
    private int DriverID;
    private String DriverName;
    private String DriverImage;
    private String DriverPhone;
    private String NumberMoto;
    private String Date;
    private int AmountBill;
    private double Rate;
    private int Active;

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public String getDriverImage() {
        return DriverImage;
    }

    public void setDriverImage(String driverImage) {
        DriverImage = driverImage;
    }

    public String getDriverPhone() {
        return DriverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        DriverPhone = driverPhone;
    }

    public String getNumberMoto() {
        return NumberMoto;
    }

    public void setNumberMoto(String numberMoto) {
        NumberMoto = numberMoto;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getAmountBill() {
        return AmountBill;
    }

    public void setAmountBill(int amountBill) {
        AmountBill = amountBill;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public Driver()
    {

    }
}
