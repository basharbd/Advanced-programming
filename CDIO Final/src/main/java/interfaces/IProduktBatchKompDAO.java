package interfaces;

import data.ProduktBatchKompDTO;
import exception.DALException;

import java.util.List;

public interface IProduktBatchKompDAO {
    ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException;
    List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException;
    List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException;
    void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;
    void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException;

}
