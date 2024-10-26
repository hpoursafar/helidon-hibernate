package ir.tamin.smsservicehh.service;

import ir.tamin.smsservicehh.model.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("request")
public class RequestService {
    @PersistenceContext(unitName = "SmsServiceDS")
    private EntityManager entityManager;

    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Long createRequest() {
        Request request = new Request();
        try {
            request.setId(111L);
            request.setComment("first insert with Helidon");
            request.setDeliverCode("123");
            request.setRefrenceid("1234567890");
            request.setRefCode("1000000000");
            request.setUserName("0077849541");

            entityManager.persist(request);
            return  request.getId();
        } catch (Exception e) {
            throw new BadRequestException("Unable to create request with ID " + request.getId());
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRequests(){
        List<Request> requests = entityManager.createQuery("select d from Request d where d.userName= :user")
                .setParameter("user","0077849541")
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();
        return Response.accepted(requests).build();
    }
}
