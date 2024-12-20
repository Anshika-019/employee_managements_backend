package employee_management.demo.Service;

import employee_management.demo.Service.mapper.EmployeeMapper;
import employee_management.demo.controller.EmployeeController;
import employee_management.demo.domain.Employee;
import employee_management.demo.dto.EmployeeDTO;
import employee_management.demo.repo.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeDTO saveEmployeeProfile(EmployeeController.EmployeeInput input) {

        Employee entity = new Employee();
        entity.setName(input.getName());
        entity.setAge(input.getAge());
        entity.setEmpClass(input.getEmpClass());
        entity.setAttendance(input.getAttendance());
        entity.setSubjects(input.getSubjects());

        // Save entity to database
        Employee savedEntity = employeeRepository.save(entity);
        // Logic to save the employee and return EmployeeDTO
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L); // Example ID
        employeeDTO.setName(input.getName());
        employeeDTO.setAge(input.getAge());
        employeeDTO.setEmpClass(input.getEmpClass());
        employeeDTO.setSubjects(input.getSubjects());
        employeeDTO.setAttendance(input.getAttendance());
        return employeeDTO;
    }

    public EmployeeDTO updateEmployeeProfile(Long id, EmployeeController.EmployeeInput input) {
        // Fetch the existing entity
        Employee existingEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + id + " not found"));

        // Update the fields
        existingEntity.setName(input.getName());
        existingEntity.setAge(input.getAge());
        existingEntity.setEmpClass(input.getEmpClass());
        existingEntity.setAttendance(input.getAttendance());
        existingEntity.setSubjects(input.getSubjects());

        // Save the updated entity
        Employee updatedEntity = employeeRepository.save(existingEntity);

        // Convert updated entity to DTO
        return employeeMapper.toDto(updatedEntity);
    }



    public EmployeeDTO deleteEmployeeProfile(Long id) {
        log.info("to delete patient: {}", id);
        EmployeeDTO deleted = getEmployeeProfile(id);
        employeeRepository.deleteById(id);
        return deleted;

    }

    public EmployeeDTO getEmployeeProfile(Long id) {
        log.info("service to get patient by id: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee != null) {
            return employeeMapper.toDto(employee);
        }else {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
    }

    public List<EmployeeDTO> getAllEmployeeProfile() {
        log.info("to get all employee documents");
        List<Employee> patientDocuments = employeeRepository.findAll();
        return employeeMapper.toDto(patientDocuments);
    }
}
