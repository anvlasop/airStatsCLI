package core.isolatotion;

public class UserPurchases {
    private Long userId;
    private Purchases purchases;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Purchases getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }
}
