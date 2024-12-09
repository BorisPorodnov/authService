package net.porodnov.springsecurityexample.services;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import net.porodnov.springsecurityexample.models.Application;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class AppService {
    private List<Application> applications;

    @PostConstruct
    public void loadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Application.builder()
                        .id(i)
                        .name(faker.app().name())
                        .author(faker.app().author())
                        .version(faker.app().version())
                        .build()
                ).toList();
    }

    public List<Application> allApplications() {
        return applications;
    }

    public Application applicationByID(int id) {
        return applications.stream()
                .filter(app -> app.getId() == id)
                .findFirst()
                .orElse(null);
    }
}