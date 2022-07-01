package hcmute.edu.vn.foody_28.Domain;

public class Voucher
{
    private int VoucherID;
    private String VoucherName;
    private String VoucherImage;
    private int Active;
    private String Date;
    private String RetireDate;
    private int Amount;
    private int AmountUsed;

    public int getAmountUsed()
    {
        return AmountUsed;
    }

    public void setAmountUsed(int amountUsed)
    {
        AmountUsed = amountUsed;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Voucher()
    {

    }

    public int getVoucherID() {
        return VoucherID;
    }

    public void setVoucherID(int voucherID) {
        VoucherID = voucherID;
    }

    public String getVoucherName() {
        return VoucherName;
    }

    public void setVoucherName(String voucherName) {
        VoucherName = voucherName;
    }

    public String getVoucherImage() {
        return VoucherImage;
    }

    public void setVoucherImage(String voucherImage) {
        VoucherImage = voucherImage;
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

    public String getRetireDate() {
        return RetireDate;
    }

    public void setRetireDate(String retireDate) {
        RetireDate = retireDate;
    }
}
