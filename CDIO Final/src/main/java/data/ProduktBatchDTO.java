package data;

import exception.DALException;
import interfaces.IProduktBatchDAO;

import java.util.List;

public class ProduktBatchDTO {
    private int pbId;
    private int receptId;
    private int status;

    public ProduktBatchDTO() {
    }

    public ProduktBatchDTO(int pbId, int receptId, int status) {
        this.pbId = pbId;
        this.receptId = receptId;
        this.status = status;
    }

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }


}
