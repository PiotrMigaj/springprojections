package pl.migibud.springprojections.employee;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.migibud.springprojections.address.AddressQueryRepository;

@Configuration(proxyBeanMethods = false)
class EmployeeConfig {

    @Bean
    EmployeeService employeeService(
            EmployeeRepository employeeRepository,
            AddressQueryRepository addressQueryRepository,
            EntityManager entityManager
    ){
        return new EmployeeService(employeeRepository,addressQueryRepository,entityManager);
    }

}
