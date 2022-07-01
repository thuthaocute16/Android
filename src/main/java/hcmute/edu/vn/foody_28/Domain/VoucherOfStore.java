package hcmute.edu.vn.foody_28.Domain;

public class VoucherOfStore
{
    private int VoucherID;
    private int StoreID;
    private int Active;

    public int getVoucherID()
    {
        return VoucherID;
    }
    public void setVoucherID(int voucherID) {
        VoucherID = voucherID;
    }
    public int getStoreID() {
        return StoreID;
    }
    public void setStoreID(int voucherStore) {
        StoreID = voucherStore;
    }
    public int getActive() {
        return Active;
    }
    public void setActive(int active) {
        Active = active;
    }

    public VoucherOfStore()
    {

    }



}
