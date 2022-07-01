package hcmute.edu.vn.foody_28.Domain;

public class Bill
{
    private int BillID;
    private int OrderID;
    private int VoucherID;
    private int Active;
    private int DriverID;
    private String Note;
    private double RiceRoot;
    private double RiceSale;
    private String Date;
    private String TimeBill;
    private String TimeComplete;
    private String Status;

    public String getStatus()
    {
        return Status;
    }

    public void setStatus(String status)
    {
        Status = status;
    }

    public String getTimeBill()
    {
        return TimeBill;
    }

    public void setTimeBill(String timeBill) {
        TimeBill = timeBill;
    }

    public String getTimeComplete() {
        return TimeComplete;
    }

    public void setTimeComplete(String timeComplete) {
        TimeComplete = timeComplete;
    }

    public int getBillID()
    {
        return BillID;
    }

    public void setBillID(int bill)
    {
        BillID = bill;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int voucherID) {
        VoucherID = voucherID;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public double getRiceRoot() {
        return RiceRoot;
    }

    public void setRiceRoot(double riceRoot) {
        RiceRoot = riceRoot;
    }

    public double getRiceSale() {
        return RiceSale;
    }

    public void setRiceSale(double riceSale) {
        RiceSale = riceSale;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Bill()
    {

    }

}
