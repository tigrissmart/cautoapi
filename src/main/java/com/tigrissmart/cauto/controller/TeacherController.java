package com.tigrissmart.cauto.controller;


import com.tigrissmart.cauto.dto.TeacherDto;

import com.tigrissmart.cauto.service.impl.TeacherServiceImpl;
import com.tigrissmart.cauto.util.ApiPaths;
import com.tigrissmart.cauto.util.TPage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Component
@RequestMapping(ApiPaths.TeacherCtrl.CTRL)
@Slf4j
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherServiceImpl;

    @GetMapping("/pagination")
    public ResponseEntity<TPage<TeacherDto>> getAllByPagination(Pageable pageable) {
        TPage<TeacherDto> data = teacherServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
    @GetMapping()
    public ResponseEntity<List<TeacherDto>> getAll() {

        List<TeacherDto> data = teacherServiceImpl.getTeachers();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable(value = "id", required = true) Long id) {

        try {

            TeacherDto teacherDto = teacherServiceImpl.getById(id);
            return ResponseEntity.ok(teacherDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }


    }

    @PostMapping
    public ResponseEntity<TeacherDto> create(@Valid @RequestBody TeacherDto teacherDto) {

        try {

            TeacherDto newTeacher= teacherServiceImpl.save(teacherDto);

            return ResponseEntity.created(new URI(ApiPaths.StudentCtrl.CTRL+"/"+newTeacher.getId())).body(newTeacher);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody TeacherDto teacher) {
        try {
            teacherServiceImpl.update(id, teacher);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id", required = true) Long id) {
        try {
            if(id!=null)
            {

                teacherServiceImpl.delete(id);
                return ResponseEntity.noContent().build();
            }
            else
            {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
}
