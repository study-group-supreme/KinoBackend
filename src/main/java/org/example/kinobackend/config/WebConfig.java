package org.example.kinobackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// SCRUM-261: lets the frontend (another port) call the API.
// Without this, the browser blocks every fetch with a CORS error.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        // this tule covers every url hat starts with /api/
        registry.addMapping("/api/**")
                // Pages from any address may call it. After deployment, could be replaced with actual site
                .allowedOrigins("*")
                // Apparently spring only allows get, head, post by default. so here we are adding put and delete
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
