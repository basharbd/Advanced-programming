package service;
import data.BrugerDTO;
import exception.DALException;
import function.BrugerDAO;

import javax.inject.Qualifier;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("bruger")
public class BrugerService {
    private static BrugerDAO dao = new BrugerDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(BrugerDTO opr) throws DALException {
        dao.createBruger(opr);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BrugerDTO> getAllUsers() throws DALException {
        return dao.getBrugerList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public BrugerDTO findUser(@PathParam("id") int id) throws DALException {
        return dao.getBruger(id);
    }

    @PUT
    public void updateUser(BrugerDTO opr) throws DALException {
        dao.updateBruger(opr);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("cpr/{cpr}")
    public BrugerDTO findCpr(@PathParam("cpr") String cpr) {
        return dao.findCpr(cpr);
    }


}
