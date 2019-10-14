package core.isolatotion;

import java.util.ArrayList;

public class Purchases {
    private Long userId;
    private ArrayList<Purchase> userPurchases;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ArrayList<Purchase> getPurchases() {
        return userPurchases;
    }

    public void setPurchases(ArrayList<Purchase> userPurchases) {
        this.userPurchases = userPurchases;
    }
}
