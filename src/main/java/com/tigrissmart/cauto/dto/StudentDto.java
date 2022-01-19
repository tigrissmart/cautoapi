package com.tigrissmart.cauto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;

    @NotNull
    private String no;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private String address;


}
