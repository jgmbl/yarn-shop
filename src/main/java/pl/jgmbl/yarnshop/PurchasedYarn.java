package pl.jgmbl.yarnshop;

import jakarta.persistence.*;
import pl.jgmbl.yarnshop.product.Yarn;

@Entity
public class PurchasedYarn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "yarn_id")
    private Yarn yarn;

    private Integer count;

    public PurchasedYarn() {
    }

    public PurchasedYarn(Purchase purchase, Yarn yarn, Integer count) {
        this.purchase = purchase;
        this.yarn = yarn;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }


    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Yarn getYarn() {
        return yarn;
    }

    public void setYarn(Yarn yarn) {
        this.yarn = yarn;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
