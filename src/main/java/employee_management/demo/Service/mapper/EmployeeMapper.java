package employee_management.demo.Service.mapper;

import employee_management.demo.domain.Employee;
import employee_management.demo.dto.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named("EmployeeMapper")
@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {
}
