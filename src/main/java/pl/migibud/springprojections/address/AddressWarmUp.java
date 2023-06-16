package pl.migibud.springprojections.address;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AddressWarmUp {

    private final AddressRepository addressRepository;

    @EventListener(ContextRefreshedEvent.class)
    void initDb(){
        addressRepository.save(
                new Address("ul. Długa 16, 51-180 Psary")
        );
    }
}
