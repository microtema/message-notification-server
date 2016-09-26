package de.seven.fate.rest;

import de.seven.fate.message.resource.MessageResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/rest")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        // Packages to be scanned for REST annotations
        register(MessageResource.class);
    }
}