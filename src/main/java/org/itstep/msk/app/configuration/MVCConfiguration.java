package org.itstep.msk.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class MVCConfiguration {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/info").setViewName("info");
        registry.addViewController("/").setViewName("info");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin").setViewName("admin");
    }
}
