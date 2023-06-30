package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.rest.client.reactive.ReactiveClientHeadersFactory;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class MyReactiveHeadersFactory extends ReactiveClientHeadersFactory{
    @Inject
    JsonWebToken jsonWebToken;

    @Override
    public Uni<MultivaluedMap<String, String>> getHeaders(MultivaluedMap<String, String> incomingHeaders,
            MultivaluedMap<String, String> clientOutgoingHeaders) {
        return Uni.createFrom().item(() -> {
            MultivaluedMap<String, String> newHeaders = new MultivaluedHashMap<>();

            newHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jsonWebToken.getRawToken());

            return newHeaders;
        });
    }
    
}
