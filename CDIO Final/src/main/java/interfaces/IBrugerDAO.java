package interfaces;

import data.BrugerDTO;
import exception.DALException;

import java.util.List;

public interface IBrugerDAO {

    BrugerDTO getBruger(int oprId) throws DALException;
    List<BrugerDTO> getBrugerList() throws DALException;
    void createBruger(BrugerDTO opr) throws DALException;
    void updateBruger(BrugerDTO opr) throws DALException;
}
