package dev.springforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ConfigurationPropertiesScan
@ComponentScan(
    basePackages = "dev.springforge",
    excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.REGEX, pattern = "dev\\.springforge\\.t3_.*")
    }
)
public class SpringForgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringForgeApplication.class, args);
    }
}
