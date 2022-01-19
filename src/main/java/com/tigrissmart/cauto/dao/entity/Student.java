package com.tigrissmart.cauto.dao.entity;

import com.tigrissmart.cauto.dao.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Builder

@Table(name = "Students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id",nullable = false)
    private Long id;

    @Column(name = "Number")
    private String no;

    @Column(name = "Firstname")
    private String firstname;

    @Column(name = "Lastname")
    private String lastname;

    @Column(name = "Address")
    private String address;

}
