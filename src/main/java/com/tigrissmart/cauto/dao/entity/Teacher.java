package com.tigrissmart.cauto.dao.entity;


import com.tigrissmart.cauto.dao.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Lastname")
    private String lastname;
}
