package com.libou.demo.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer account_id;

    private String user;

    private String action;

    @Column(name = "transaction_date")
    @CreatedDate
    private Date date;

    private int amount;

    public Transaction(){};

    public Transaction(Integer account_id, String user, String action, int amount){
        this.account_id = account_id;
        this.user = user;
        this.action = action;
        this.amount = amount;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public Integer getAccount_id() {
//        return account_id;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public String getAction() {
//        return action;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setAccount_id(Integer account_id) {
//        this.account_id = account_id;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
}
