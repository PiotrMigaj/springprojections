package pl.migibud.springprojections.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @GetMapping("api/hello")
    String sayHello(){
        return "Hello from Spring app...";
    }

}
