package interfaces;

import data.ProduktBatchDTO;
import exception.DALException;

import java.util.List;

public interface IProduktBatchDAO {
    ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
    List<ProduktBatchDTO> getProduktBatchList() throws DALException;
    void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
    void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
}
