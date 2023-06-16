package pl.migibud.springprojections.employee;

record EmployeeCreationRequest(
        String firstName,
        String lastName,
        Long addressId
) {
}
