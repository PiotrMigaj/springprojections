package pl.migibud.springprojections.employee;

import org.springframework.beans.factory.annotation.Value;

interface EmployeeView {
    String getFirstName();
    String getLastName();
    String getAddress();
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}
