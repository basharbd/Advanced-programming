package service;
import data.ReceptDTO;
import exception.DALException;
import function.ReceptDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("recept")
public class ReceptService {

    private static ReceptDAO receptdao = new ReceptDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRecipe(ReceptDTO recept) throws DALException {
        receptdao.createRecept(recept);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReceptDTO> getAllRecipes() throws DALException {
        return receptdao.getReceptList();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public ReceptDTO findRecipe(@PathParam("id") int id) throws DALException {
        return receptdao.getRecept(id);
    }

    @PUT
    public void updateRecipe(ReceptDTO recept) throws DALException {
        receptdao.updateRecept(recept);
    }
}
