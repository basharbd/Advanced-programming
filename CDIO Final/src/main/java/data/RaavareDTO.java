package data;


public class RaavareDTO{

    private int raavareId;
    private String raavareNavn;

    public RaavareDTO() {
    }

    public RaavareDTO(int raavareId, String raavareNavn) {
        this.raavareId = raavareId;
        this.raavareNavn = raavareNavn;
    }

    public int getRaavareId() {
        return raavareId;
    }

    public void setRaavareId(int raavareId) {
        this.raavareId = raavareId;
    }

    public String getRaavareNavn() {
        return raavareNavn;
    }

    public void setRaavareNavn(String raavareNavn) {
        this.raavareNavn = raavareNavn;
    }
}
