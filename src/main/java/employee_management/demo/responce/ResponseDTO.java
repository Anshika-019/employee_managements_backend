package employee_management.demo.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {

    private Boolean isSuccess;
    private T data;
    private String status;
    private EmployeeError error;

    @Data
    public static class EmployeeError {

        private List<String> errors;
        private String code;
        private String message;
    }

    public ResponseDTO(T data) {
        this.data = data;
        this.isSuccess = true;
        this.status = HttpStatus.OK.toString();
    }

}
