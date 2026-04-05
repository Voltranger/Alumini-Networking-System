package com.pict.Alumni.entity;
import com.pict.alumni.entity.Alumni;
import jakarta.persistence.*;
@Entity
@Table(name="job")
public class Job {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;

    private String title;
    private String company;
    private String description;

    @ManyToOne
    @JoinColumn(name="alumini_id")
    private Alumni postedBy;



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Alumni getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Alumni postedBy) {
        this.postedBy = postedBy;
    }
}
