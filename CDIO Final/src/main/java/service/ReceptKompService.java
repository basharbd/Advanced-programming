package service;
import data.ReceptKompDTO;
import exception.DALException;
import function.ReceptKompDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rkomp")
public class ReceptKompService {

    private static ReceptKompDAO komp = new ReceptKompDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRecipeComp(ReceptKompDTO receptkomponent) throws DALException {
        komp.createReceptKomp(receptkomponent);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptKompDTO> getAllRecipeComp() throws DALException {
        return komp.getReceptKompList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("komp/{id}")
    public List<ReceptKompDTO> getAllRecipeComp(@PathParam("id")int receptId) throws DALException {
        return komp.getReceptKompList(receptId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{receptId}/{raavareId}")
    public ReceptKompDTO findRecipeComp(@PathParam("receptId") int receptId, @PathParam("raavareId") int raavareId) throws DALException {
        return komp.getReceptKomp(receptId, raavareId);

    }
    @PUT
    public void updateRecipeComp(ReceptKompDTO receptkomponent) throws DALException {
        komp.updateReceptKomp(receptkomponent);
    }
}
