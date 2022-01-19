package com.tigrissmart.cauto.controller;

import com.tigrissmart.cauto.dto.CourseDto;
import com.tigrissmart.cauto.dto.TeacherDto;
import com.tigrissmart.cauto.service.impl.CourseServiceImpl;
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
@RequestMapping(ApiPaths.CourseCtrl.CTRL)
@Slf4j
@CrossOrigin
@NoArgsConstructor
@AllArgsConstructor
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/pagination")
    public ResponseEntity<TPage<CourseDto>> getAllByPagination(Pageable pageable) {
        TPage<CourseDto> data = courseService.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }
    @GetMapping()
    public ResponseEntity<List<CourseDto>> getAll() {

        List<CourseDto> data = courseService.getCourses();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable(value = "id", required = true) Long id) {

        try {

            CourseDto courseDto = courseService.getById(id);
            return ResponseEntity.ok(courseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); //return 404, with null body
        }


    }

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto courseDto) {

        try {

            CourseDto newCourse= courseService.save(courseDto);

            return ResponseEntity.created(new URI(ApiPaths.StudentCtrl.CTRL+"/"+newCourse.getId())).body(newCourse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody CourseDto course) {
        try {
            courseService.update(id, course);
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

                courseService.delete(id);
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
