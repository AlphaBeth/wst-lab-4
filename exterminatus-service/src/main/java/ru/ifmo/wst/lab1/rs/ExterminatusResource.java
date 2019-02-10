package ru.ifmo.wst.lab1.rs;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.dao.ExterminatusDAO;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/exterminatus")
@Produces({MediaType.APPLICATION_JSON})
public class ExterminatusResource {

    public static ExterminatusDAO GLOBAL_DAO;

    @Inject
    private ExterminatusDAO exterminatusDAO;

    public ExterminatusResource() {
        exterminatusDAO = GLOBAL_DAO;
    }

    @GET
    @Path("/all")
    @SneakyThrows
    public List<ExterminatusEntity> findAll() {
        return exterminatusDAO.findAll();
    }

    @GET
    @Path("/filter")
    @SneakyThrows
    public List<ExterminatusEntity> filter(@QueryParam("id") Long id, @QueryParam("initiator") String initiator,
                                           @QueryParam("reason") String reason, @QueryParam("method") String method,
                                           @QueryParam("planet") String planet, @QueryParam("date") Date date) {
        return exterminatusDAO.filter(id, initiator, reason, method, planet, date);
    }
}
