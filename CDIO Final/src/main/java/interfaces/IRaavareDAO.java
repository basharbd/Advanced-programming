package interfaces;

import data.RaavareDTO;
import exception.DALException;

import java.util.List;

public interface IRaavareDAO {
    RaavareDTO getRaavare(int raavareId) throws DALException;
    List<RaavareDTO> getRaavareList() throws DALException;
    void createRaavare(RaavareDTO raavare) throws DALException;
    void updateRaavare(RaavareDTO raavare) throws DALException;



}
