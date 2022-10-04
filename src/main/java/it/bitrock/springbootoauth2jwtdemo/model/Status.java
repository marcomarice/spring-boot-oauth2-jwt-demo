package it.bitrock.springbootoauth2jwtdemo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status", schema = "public")
@Getter
@Setter
public class  Status implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Curriculum> curricula = new ArrayList<>();
}
