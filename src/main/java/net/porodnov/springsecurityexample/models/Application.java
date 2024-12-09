package net.porodnov.springsecurityexample.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Application {
    private int id;
    private String name;
    private String author;
    private String version;
}
