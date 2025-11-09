package com.example.studentsystem.Controller;

import com.example.studentsystem.Api.ApiResponse;
import com.example.studentsystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Student> firstHonors = new ArrayList<>();
    ArrayList<Student> secondHonors= new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents (){
        return students;
    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
        students.add(student);
        return new ApiResponse("Student " + student.getName() + " has been added with ID " + student.getId() );
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent (@PathVariable int index, @RequestBody Student student){
        students.set(index,student);
        return new ApiResponse("Student has been updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index){
        students.remove(index);
        return new ApiResponse("Student has been removed");
    }

    @GetMapping("/honors/{id}") // id examples (0001, 0002, ..)
    public ApiResponse displayHonor(@PathVariable String id) {
        for (Student s : students){
            if(s.getId().equals(id) && s.getGpa() >= 4.75){
                return new ApiResponse("Student " +s.getName() + " is a first honor, with Gpa: " + s.getGpa());
            } else if (s.getId().equals(id) && s.getGpa() < 4.75 && s.getGpa() >=4.25){
                return new ApiResponse("Student " +s.getName() + " is a second honor, with Gpa: " + s.getGpa());
            } else if (s.getId().equals(id) && s.getGpa() < 4.25){
                return new ApiResponse("Student " +s.getName() + " is neither a first nor second honor, with Gpa: " + s.getGpa());
            }
        }
        return new ApiResponse("Couldn't find Student with the provided id");
    }


    @GetMapping("/above-average")
    public ArrayList<Student> getStudentWithGpaAboveAverage(){
        ArrayList<Student> studentsAboveAvg = new ArrayList<>();
        double avgGpa =0;
        for (Student s: students){
            avgGpa += s.getGpa();
        }
        avgGpa /= students.size();

        for (Student s : students){
            if(s.getGpa() > avgGpa){
                studentsAboveAvg.add(s);
            }
        }
      return studentsAboveAvg;
    }


}
