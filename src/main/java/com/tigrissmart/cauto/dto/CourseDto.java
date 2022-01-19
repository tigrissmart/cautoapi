package com.tigrissmart.cauto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String description;
}
