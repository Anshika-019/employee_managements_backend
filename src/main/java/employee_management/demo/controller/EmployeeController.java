package employee_management.demo.controller;


import employee_management.demo.Service.EmployeeService;
import employee_management.demo.dto.EmployeeDTO;
import employee_management.demo.responce.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@Validated
@Tag(name = "Employee", description = "Employee API")
@RequiredArgsConstructor
@Controller
public class EmployeeController {

    private final EmployeeService employeeService;


    @MutationMapping("createEmployee")
    public EmployeeDTO save(@Argument EmployeeInput input){
        log.info("Going to save employee profile");
        EmployeeDTO data = employeeService.saveEmployeeProfile(input);
        return data;
    }

    @MutationMapping("updateEmployee")
    public EmployeeDTO update(@Argument Long id,@Argument EmployeeInput input) {
        log.info("Going to update employee profile");
        return employeeService.updateEmployeeProfile(id,input);
    }

    @MutationMapping("deleteEmployee")
    public EmployeeDTO delete(@Argument Long id){
        log.info("Going to delete employee profile");
        EmployeeDTO data = employeeService.deleteEmployeeProfile(id);
        return data;
    }

    @QueryMapping("getEmployee")
    public EmployeeDTO getById(@Argument Long id){
        log.info("Going to get employee profile");
        EmployeeDTO data = employeeService.getEmployeeProfile(id);
        return data;
    }

    @QueryMapping("findAllEmployees")
    public List<EmployeeDTO> getAll() {
        log.info("Get all patient documents");
        List<EmployeeDTO> data = employeeService.getAllEmployeeProfile();
        return data;
    }

    @Data
    public static class EmployeeInput {
        private Long id;
        private String name;
        private int age;
        private String empClass;
        private double attendance;
        private String subjects;
    }
}
