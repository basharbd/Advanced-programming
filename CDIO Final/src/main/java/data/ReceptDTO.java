package data;

public class ReceptDTO {
    private int receptId;
    private String receptNavn;

    public ReceptDTO() {
    }

    public ReceptDTO(int receptId, String receptNavn) {
        this.receptId = receptId;
        this.receptNavn = receptNavn;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public String getReceptNavn() {
        return receptNavn;
    }

    public void setReceptNavn(String receptNavn) {
        this.receptNavn = receptNavn;
    }

}
