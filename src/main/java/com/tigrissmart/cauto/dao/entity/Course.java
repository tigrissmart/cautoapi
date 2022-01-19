package com.tigrissmart.cauto.dao.entity;

import com.tigrissmart.cauto.dao.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
}
