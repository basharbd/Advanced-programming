package service;
import data.RaavareDTO;
import exception.DALException;
import function.RaavareDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("raavare")
public class RaavareService {

    private static RaavareDAO dao = new RaavareDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createItem(RaavareDTO raavare) throws DALException {
        dao.createRaavare(raavare);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RaavareDTO> getAllItems() throws DALException {
        return dao.getRaavareList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public RaavareDTO findItem(@PathParam("id") int id) throws DALException {
        return dao.getRaavare(id);

    }
    @PUT
    public void updateItem(RaavareDTO raavare) throws DALException {
        dao.updateRaavare(raavare);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("itemname/{id}")
    public ArrayList<String> showItemName(@PathParam("id") int raavareId) {
        return dao.visRaavareNavn(raavareId);
    }
}
