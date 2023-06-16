package pl.migibud.springprojections.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
               select 
                   e.firstName as firstName,
                   e.lastName as lastName,
                   a.address as address 
               from Employee e 
               join Address a 
               where e.id =:id   
            """)
    Optional<EmployeeView> getEmployeeInfo(@Param("id") long id);
}
