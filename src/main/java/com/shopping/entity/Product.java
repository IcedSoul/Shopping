package com.shopping.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by 14437 on 2017/3/1.
 */
@Entity
@Table(name="products")
public class Product {
    private int id;
    private String name;
    private String description;
    private String keyWord;
    private int price;
    private int counts;
    private int type;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //设置主键自增
    @GeneratedValue(generator = "generator")

    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="key_word")
    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Column(name="price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name="counts")
    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Column(name="type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
