package com.darin.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_veiws")
public class Upvote {

    public Upvote(Long id,Long votes){
        this.id = id;
        this.encourage = votes;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long encourage;

    public Upvote() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEncourage() {
        return encourage;
    }

    public void setEncourage(Long encourage) {
        this.encourage = encourage;
    }

    @Override
    public String toString() {
        return "views{" +
                "id=" + id +
                ", encourage=" + encourage +
                '}';
    }
}
