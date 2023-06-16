package pl.migibud.springprojections.address;

import java.util.Optional;

public interface AddressQueryRepository {

    Optional<Address> findById(Long id);

}
