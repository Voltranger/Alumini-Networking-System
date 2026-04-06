package com.pict.alumni.entity;


import jakarta.persistence.*;

@Entity
@Table(name="connection")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name="alumni1_id")
    private Alumni alumni1;

    @ManyToOne
    @JoinColumn(name="alumni2_id")
    private Alumni alumni2;

    public void setId(Long id) {
        this.id = id;
    }

    public void setAlumni1(Alumni alumni1) {
        this.alumni1 = alumni1;
    }

    public void setAlumni2(Alumni alumni2)
    {
        this.alumni2=alumni2;
    }

    public Alumni getAlumni1() {
        return alumni1;
    }

    public Alumni getAlumni2() {
        return alumni2;
    }
}

