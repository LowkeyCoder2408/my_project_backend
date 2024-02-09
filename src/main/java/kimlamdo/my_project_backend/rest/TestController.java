package kimlamdo.my_project_backend.rest;

import kimlamdo.my_project_backend.dao.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private OrderDetailRepository repository;

    @Autowired
    public TestController(OrderDetailRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public void test() {

    }
}