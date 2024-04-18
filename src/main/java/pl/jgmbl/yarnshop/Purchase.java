package pl.jgmbl.yarnshop;

import jakarta.persistence.*;
import pl.jgmbl.yarnshop.user.User;

import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date date;
    private String state;

    public Purchase() {
    }

    public Purchase(User user, Date date, String state) {
        this.user = user;
        this.date = date;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", date=" + date +
                ", state='" + state + '\'' +
                '}';
    }
}
