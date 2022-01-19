package com.tigrissmart.cauto.controller;

import com.tigrissmart.cauto.dto.StudentDto;
import com.tigrissmart.cauto.service.impl.StudentServiceImpl;
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
@RequestMapping(ApiPaths.StudentCtrl.CTRL)
@Slf4j
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/pagination")
    public ResponseEntity<TPage<StudentDto>> getAllByPagination(Pageable pageable) {
        TPage<StudentDto> data = studentServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAll() {

        List<StudentDto> data = studentServiceImpl.getStudents();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable(value = "id", required = true) Long id) {

        try {

            StudentDto studentDto = studentServiceImpl.getById(id);
            return ResponseEntity.ok(studentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }


    }

    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto studentDto) {

        try {

            StudentDto newStudent= studentServiceImpl.save(studentDto);

            return ResponseEntity.created(new URI(ApiPaths.StudentCtrl.CTRL+"/"+newStudent.getId())).body(newStudent);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody StudentDto student) {
        try {
            studentServiceImpl.update(id, student);
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

                studentServiceImpl.delete(id);
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
