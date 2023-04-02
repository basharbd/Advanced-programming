package service;
import data.ProduktBatchDTO;
import exception.DALException;
import function.ProduktBatchDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("pbatch")
public class ProduktBatchService {

    private static ProduktBatchDAO dao = new ProduktBatchDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProductBatch(ProduktBatchDTO produktbatch) throws DALException {
        dao.createProduktBatch(produktbatch);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchDTO> getAllProductBatches() throws DALException {
        return dao.getProduktBatchList();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ProduktBatchDTO findProductBatch(@PathParam("id") int id) throws DALException {
        return dao.getProduktBatch(id);
    }

    @PUT
    public void updateProductBatch(ProduktBatchDTO produktbatch) throws DALException {
        dao.updateProduktBatch(produktbatch);
    }
}
