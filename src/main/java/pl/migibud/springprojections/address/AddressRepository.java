package pl.migibud.springprojections.address;

import org.springframework.data.jpa.repository.JpaRepository;

interface AddressRepository extends AddressQueryRepository, JpaRepository<Address,Long> {
}
