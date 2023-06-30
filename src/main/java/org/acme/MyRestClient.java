package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Uni;

@RegisterRestClient
@RegisterClientHeaders(MyReactiveHeadersFactory.class)
public interface MyRestClient {
    @Path("/hello")
    @GET
    Uni<String> hello();
}