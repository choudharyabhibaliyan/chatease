package org.durga.config.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
@Component
public class CrosCustomizer {


    private static final String ALLOWED_HEADERS = "X-Requested-With,access-control-allow-origin,Authorization,authorization,Origin,Content-Type,Version";
    private static final String ALLOWED_METHODS = "GET,PUT,POST,DELETE,OPTIONS";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600";
    public void corsCustomizer(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration cc = new CorsConfiguration();
//              cc.setAllowCredentials();
                ArrayList list= new ArrayList();
                list.add("http://localhost:4200");
                cc.setAllowedOrigins(Collections.singletonList("*"));
                ArrayList list1= new ArrayList();
                list1.add("*");
                cc.addAllowedHeader("*");
                cc.setAllowedMethods(Arrays.asList("GET", "POST","PUT","DELETE","OPTIONS"));
                return cc;
            };

            c.configurationSource(source);
        });
    }
}
