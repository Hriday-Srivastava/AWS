package com.example.k8scruddemo;

import com.example.k8scruddemo.entity.Student;
import com.example.k8scruddemo.entity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
@SpringBootApplication
public class K8scruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(K8scruddemoApplication.class, args);
	}


    @Autowired
    private StudentRepository repository;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    @PostMapping("/add")
    public String  addStudent(@RequestBody Student student){
        repository.save(student);
        return "Student is registered successfully !";
    }

}
