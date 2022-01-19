package com.tigrissmart.cauto.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private Long id;

    @NotNull
    private String firstname;
    @NotNull
    private String lastname;

}
