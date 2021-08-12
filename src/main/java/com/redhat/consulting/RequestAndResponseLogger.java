package com.redhat.consulting;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

/**
 * @author rlichti
 * @version 1.0.0 2021-08-12
 * @since 1.0.0 2021-08-12
 */
@Slf4j
@Provider
public class RequestAndResponseLogger implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) {
        log.info(
                "Request: headers={}, cookies={}, user='{}'",
                request.getHeaders(),
                request.getCookies(),
                readUserFromRequestContext(request)
        );
    }

    private String readUserFromRequestContext(ContainerRequestContext request) {
        try {
            return request.getSecurityContext().getUserPrincipal().getName();
        } catch (Exception e) {
            // do nothing - it's only logging.
        }

        return "./.";
    }
}
