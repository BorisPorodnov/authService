package net.porodnov.springsecurityexample.controllers;

import lombok.AllArgsConstructor;
import net.porodnov.springsecurityexample.models.Application;
import net.porodnov.springsecurityexample.services.AppService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/apps")
@AllArgsConstructor
public class AppController {

    private AppService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the unprotected page";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApp() {
        return service.allApplications();
    }

    @GetMapping("/{id}")
    public Application appById(@PathVariable int id) {
        return service.applicationByID(id);
    }

}
