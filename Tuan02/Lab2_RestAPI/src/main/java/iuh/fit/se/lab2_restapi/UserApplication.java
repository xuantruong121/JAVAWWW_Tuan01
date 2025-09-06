package iuh.fit.se.lab2_restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class UserApplication extends ResourceConfig {
    public UserApplication() {
        packages("iuh.fit.se.lab2_restapi");

        // Đăng ký Jackson JavaTimeModule
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }
}
