package com.example.SecuritywithLeaners.Controller;


import com.example.SecuritywithLeaners.DTO.ResponseDTO;
import com.example.SecuritywithLeaners.DTO.StudentDTO;
import com.example.SecuritywithLeaners.Entity.Student;
import com.example.SecuritywithLeaners.Service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping( "api/admin")
@CrossOrigin
public class AdminServicesController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/registerStudent")
    public ResponseEntity registerStudent(@RequestBody Student studentDTO){

        System.out.println("request received");
        ResponseDTO responseDTO = adminService.saveStudent(studentDTO);
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }
    @GetMapping("/getStudent")
    public ResponseEntity getAllStudentSize(){
        ResponseDTO responseDTO = adminService.getAllStudentSize();
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }
    @GetMapping("/getStudent/{field}/{order}")
    public ResponseEntity getStudentWithOrder(@PathVariable String field,@PathVariable String order){
        ResponseDTO responseDTO = adminService.getStudentBySorting(field,order);
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }

    @GetMapping("/getStudent/{field}/{order}/{pageSize}/{offset}")
    public ResponseEntity getStudentWithOrderAndPagination(@PathVariable String field,@PathVariable String order,@PathVariable int pageSize,@PathVariable int offset){
        ResponseDTO responseDTO = adminService.getStudentBySortingAndPagination(field,order,pageSize,offset);
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }
    @GetMapping("/getStudentBasic/{detail}")
    public ResponseEntity getStudentByDetail(@PathVariable String detail){
        System.out.println(detail);
        ResponseDTO responseDTO = adminService.getStudentByDetail(detail);
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }

    @GetMapping("/getStudentByID/{stdID}")
    public ResponseEntity getStudentByID(@PathVariable String stdID){
        ResponseDTO responseDTO = adminService.getStudentByID(stdID);
        return new ResponseEntity(responseDTO,responseDTO.getStatus());
    }
}
