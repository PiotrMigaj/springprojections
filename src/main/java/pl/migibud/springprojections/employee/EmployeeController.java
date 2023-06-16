package pl.migibud.springprojections.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Employee registerEmployee(@RequestBody EmployeeCreationRequest request){
        return employeeService.registerEmployee(request);
    }

    @GetMapping("{id}")
    EmployeeView getEmployeeInfo(@PathVariable Long id){
        return employeeService.getEmployeeInfo(id);
    }
}
