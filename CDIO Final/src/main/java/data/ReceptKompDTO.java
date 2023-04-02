package data;

public class ReceptKompDTO{
    private int receptId;
    private int raavareId;
    private double nonNetto;
    private double tolerance;

    public ReceptKompDTO() {
    }

    public ReceptKompDTO(int receptId, int raavareId, double nonNetto, double tolerance) {
        this.receptId = receptId;
        this.raavareId = raavareId;
        this.nonNetto = nonNetto;
        this.tolerance = tolerance;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public double getNonNetto() {
        return nonNetto;
    }

    public void setNonNetto(double nomNetto) {
        this.nonNetto = nomNetto;
    }

    public double getTolerance() {
        return tolerance;
    }

    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

}
