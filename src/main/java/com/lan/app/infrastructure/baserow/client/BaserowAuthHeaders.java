package com.lan.app.infrastructure.baserow.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.MultivaluedMap;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

@ApplicationScoped
public class BaserowAuthHeaders implements ClientHeadersFactory {

    @ConfigProperty(name = "baserow.token")
    String token;

    @Override
    public MultivaluedMap<String, String> update(
        MultivaluedMap<String, String> incoming,
        MultivaluedMap<String, String> outgoing
    ) {
        outgoing.putSingle("Authorization", "Token " + token);
        return outgoing;
    }
}
