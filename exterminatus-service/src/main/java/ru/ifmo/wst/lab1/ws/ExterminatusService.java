package ru.ifmo.wst.lab1.ws;

import lombok.SneakyThrows;
import ru.ifmo.wst.lab1.dao.ExterminatusDAO;
import ru.ifmo.wst.lab1.model.ExterminatusEntity;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;

@WebService
public class ExterminatusService {
    @Inject
    private ExterminatusDAO exterminatusDAO;

    @WebMethod
    @SneakyThrows
    public List<ExterminatusEntity> findAll() {
        return exterminatusDAO.findAll();
    }

    @WebMethod
    @SneakyThrows
    public List<ExterminatusEntity> filter(@WebParam(name = "id") Long id, @WebParam(name = "initiator") String initiator,
                                           @WebParam(name = "reason") String reason, @WebParam(name = "method") String method,
                                           @WebParam(name = "planet") String planet, @WebParam(name = "date") Date date) {
        return exterminatusDAO.filter(id, initiator, reason, method, planet, date);
    }

    @WebMethod
    @SneakyThrows
    public long create(@WebParam(name = "initiator") String initiator,
                       @WebParam(name = "reason") String reason, @WebParam(name = "method") String method,
                       @WebParam(name = "planet") String planet, @WebParam(name = "date") Date date) {
        return exterminatusDAO.create(initiator, reason, method, planet, date);
    }

    @WebMethod
    @SneakyThrows
    public int delete(@WebParam(name = "id") long id) {
        return exterminatusDAO.delete(id);
    }

    @WebMethod
    @SneakyThrows
    @WebResult(name = "updatedCount")
    public int update(@WebParam(name = "id") long id, @WebParam(name = "initiator") String initiator,
                      @WebParam(name = "reason") String reason, @WebParam(name = "method") String method,
                      @WebParam(name = "planet") String planet, @WebParam(name = "date") Date date) {
        return exterminatusDAO.update(id, initiator, reason, method, planet, date);
    }

    public ExterminatusService(ExterminatusDAO exterminatusDAO) {
        this.exterminatusDAO = exterminatusDAO;
    }

    public ExterminatusService() {
    }
}
