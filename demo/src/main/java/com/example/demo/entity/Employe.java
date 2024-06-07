package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ElementCollection
    @CollectionTable(name = "employe_skills", joinColumns = @JoinColumn(name = "employe_id"))
    @Column(name = "skill")
    private List<String> skills;

    @ManyToMany
    @JoinTable(
            name = "employe_projects",
            joinColumns = @JoinColumn(name = "employe_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public void addProject(Project project) {
        this.projects.add(project);
    }

}
