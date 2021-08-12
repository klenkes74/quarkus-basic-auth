package com.redhat.consulting;

import io.quarkus.security.Authenticated;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Map<String,String> hello(@Context HttpHeaders headers) {
        Map<String, String> resultMap = new HashMap<>();
        headers.getRequestHeaders().forEach(
                (key, values) -> resultMap.put(key, String.join(", ", values)));
        return resultMap;
    }

    @GET
    @Path("auth")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Map<String,String> auth(@Context HttpHeaders headers) {
        return hello(headers);
    }
}