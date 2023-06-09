package data;
import exception.DALException;
import interfaces.IProduktBatchKompDAO;

import java.util.List;

public class ProduktBatchKompDTO{
    private int pbId;
    private int rbId;
    private double tara;
    private double netto;
    private int oprId;

    public ProduktBatchKompDTO() {
    }

    public ProduktBatchKompDTO(int pbId, int rbId, double tara, double netto, int oprId) {
        this.pbId = pbId;
        this.rbId = rbId;
        this.tara = tara;
        this.netto = netto;
        this.oprId = oprId;
    }

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getRbId() {
        return rbId;
    }

    public void setRbId(int rbId) {
        this.rbId = rbId;
    }

    public double getTara() {
        return tara;
    }

    public void setTara(double tara) {
        this.tara = tara;
    }

    public double getNetto() {
        return netto;
    }

    public void setNetto(double netto) {
        this.netto = netto;
    }

    public int getOprId() {
        return oprId;
    }

    public void setOprId(int oprId) {
        this.oprId = oprId;
    }

}
