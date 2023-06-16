package pl.migibud.springprojections.employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.springprojections.address.Address;
import pl.migibud.springprojections.address.AddressQueryRepository;

import java.util.Set;


@RequiredArgsConstructor
class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AddressQueryRepository addressQueryRepository;
    private final EntityManager entityManager;

    @Transactional
    public Employee registerEmployee(EmployeeCreationRequest request){
        Address address = addressQueryRepository.findById(request.addressId())
                .orElseThrow(() -> new EntityNotFoundException("There is no address with ID=%s".formatted(request.addressId())));
        Employee employee = new Employee(
                request.firstName(),
                request.lastName(),
                Set.of(address)
        );
        Employee savedEmployee = employeeRepository.save(employee);
        entityManager.detach(savedEmployee);
        return savedEmployee;
    }

    public EmployeeView getEmployeeInfo(final long id){
        return employeeRepository.getEmployeeInfo(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no employee with ID=%s".formatted(id)));
    }
}
