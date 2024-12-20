package employee_management.demo.dto;

import employee_management.demo.domain.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private String empClass;
    private double attendance;
    private String subjects;
}
