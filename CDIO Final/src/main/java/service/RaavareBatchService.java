package service;

import data.BrugerDTO;
import data.RaavareBatchDTO;
import exception.DALException;
import function.RaavareBatchDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rbatch")
public class RaavareBatchService {

    private static RaavareBatchDAO batch = new RaavareBatchDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createItemBatch(RaavareBatchDTO raavareBatch) throws DALException {
        batch.createRaavareBatch(raavareBatch);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareBatchDTO> getAllItemBatches() throws DALException {
        return batch.getRaavareBatchList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public RaavareBatchDTO findItemBatch(@PathParam("id") int id) throws DALException {
        return batch.getRaavareBatch(id);
    }

    @PUT
    public void updateItembatch(RaavareBatchDTO raavareBatch) throws DALException {
        batch.updateRaavareBatch(raavareBatch);
    }
}
