package ru.ifmo.wst.lab1;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import lombok.Getter;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;
import ru.ifmo.wst.lab1.model.Filter;

import javax.ws.rs.core.MediaType;
import java.util.List;

public class ExterminatusResourceClient {

    public static final GenericType<List<ExterminatusEntity>> EXTERMINATUS_LIST =
            new GenericType<List<ExterminatusEntity>>() {
            };
    @Getter
    private final String baseUrl;
    private final WebResource findAllResource;
    private final WebResource filterResource;


    public ExterminatusResourceClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.findAllResource = Client.create().resource(url("/all"));
        this.filterResource = Client.create().resource(url("/filter"));
    }

    public List<ExterminatusEntity> findAll() {
        return findAllResource.accept(MediaType.APPLICATION_JSON_TYPE).get(EXTERMINATUS_LIST);
    }

    public List<ExterminatusEntity> filter(Filter filter) {
        WebResource resource = filterResource;
        resource = setParamIfNotNull(resource, "id", filter.getId());
        resource = setParamIfNotNull(resource, "initiator", filter.getInitiator());
        resource = setParamIfNotNull(resource, "method", filter.getMethod());
        resource = setParamIfNotNull(resource, "planet", filter.getPlanet());
        resource = setParamIfNotNull(resource, "reason", filter.getReason());
        resource = setParamIfNotNull(resource, "date", filter.getDate());
        return resource.accept(MediaType.APPLICATION_JSON_TYPE).get(EXTERMINATUS_LIST);
    }

    private String url(String endpointAddress) {
        return baseUrl + endpointAddress;
    }

    private WebResource setParamIfNotNull(WebResource resource, String paramName, Object value) {
        if (value == null) {
            return resource;
        }
        return resource.queryParam(paramName, value.toString());
    }
}
