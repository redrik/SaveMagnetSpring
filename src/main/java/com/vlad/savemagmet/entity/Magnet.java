package com.vlad.savemagmet.entity;

import javax.persistence.*;

@Entity
@Table(name = "magnet")
public class Magnet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String title;

    public long getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    protected Magnet() {
    }

    public Magnet(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String toString() {
        return String.format("id=%d, title='%s'",
                id, title);
    }
}