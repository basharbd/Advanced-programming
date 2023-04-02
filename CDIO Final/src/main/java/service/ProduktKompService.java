package service;

import data.ProduktBatchKompDTO;
import exception.DALException;
import function.ProduktBatchKompDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("pkomp")
public class ProduktKompService {

    private static ProduktBatchKompDAO komp = new ProduktBatchKompDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProductComp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
        komp.createProduktBatchKomp(produktbatchkomponent);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProduktBatchKompDTO> getAllProductComp() throws DALException {
        return komp.getProduktBatchKompList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("komp/{id}")
    public List<ProduktBatchKompDTO> getAllProductComp(@PathParam("id")int pbId) throws DALException {
        return komp.getProduktBatchKompList(pbId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pbId}/{rbId}")
    public ProduktBatchKompDTO findProductComp(@PathParam("pbId") int pbId, @PathParam("rbId") int rbId) throws DALException {
        return komp.getProduktBatchKomp(pbId, rbId);

    }
    @PUT
    public void updateProductComp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
        komp.updateProduktBatchKomp(produktbatchkomponent);
    }
}
