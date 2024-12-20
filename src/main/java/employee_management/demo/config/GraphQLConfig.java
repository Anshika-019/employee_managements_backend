package employee_management.demo.config;

import employee_management.demo.Service.EmployeeService;
import employee_management.demo.controller.EmployeeController;
import employee_management.demo.dto.EmployeeDTO;
import employee_management.demo.controller.EmployeeController.EmployeeInput;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Map;

@Configuration
public class GraphQLConfig {
    private final EmployeeService employeeService;

    public GraphQLConfig(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("findAllEmployees", env -> employeeService.getAllEmployeeProfile())
                        .dataFetcher("getEmployee", env -> {
                            Long id = env.getArgument("id");
                            return employeeService.getEmployeeProfile(id);
                        })
                )
                .type("Mutation", typeWiring -> typeWiring
                        .dataFetcher("createEmployee", env -> {
                            EmployeeInput input = convertToEmployeeInput(env.getArgument("input"));
                            return employeeService.saveEmployeeProfile(input);
                        })
                        .dataFetcher("updateEmployee", env -> {
                            Long id = env.getArgument("id"); // Fetch the ID argument
                            Map<String, Object> inputMap = env.getArgument("input"); // Fetch the input argument
                            EmployeeController.EmployeeInput input = convertMapToEmployeeInput(inputMap); // Convert to EmployeeInput
                            return employeeService.updateEmployeeProfile(id, input); // Pass both ID and input
                        })

                        .dataFetcher("deleteEmployee", env -> {
                            Long id = env.getArgument("id");
                            return employeeService.deleteEmployeeProfile(id);
                        })
                );
    }

    private EmployeeInput convertToEmployeeInput(EmployeeDTO dto) {
        EmployeeInput input = new EmployeeInput();
        input.setName(dto.getName());
        input.setAge(dto.getAge());
        input.setEmpClass(dto.getEmpClass());
        input.setSubjects(dto.getSubjects());
        input.setAttendance(dto.getAttendance());
        return input;
    }

    private EmployeeDTO convertToEmployeeDTO(EmployeeInput input) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(input.getName());
        dto.setAge(input.getAge());
        dto.setEmpClass(input.getEmpClass());
        dto.setSubjects(input.getSubjects());
        dto.setAttendance(input.getAttendance());
        return dto;
    }

    private EmployeeController.EmployeeInput convertMapToEmployeeInput(Map<String, Object> inputMap) {
        EmployeeController.EmployeeInput input = new EmployeeController.EmployeeInput();
        input.setId((Long) inputMap.get("id"));
        input.setName((String) inputMap.get("name"));
        input.setAge((Integer) inputMap.get("age"));
        input.setEmpClass((String) inputMap.get("empClass"));
        input.setSubjects((String) inputMap.get("subjects"));
        input.setAttendance((Double) inputMap.get("attendance"));
        return input;
    }

}
