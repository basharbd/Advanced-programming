package data;

public class BrugerDTO {

    private int oprId;
    private String oprNavn;
    private String ini;
    private String cpr;
    private String rolle;
    private String status;

    public BrugerDTO() {
    }

    public BrugerDTO(int oprId, String oprNavn, String ini, String cpr, String rolle, String status) {
        this.oprId = oprId;
        this.oprNavn = oprNavn;
        this.ini = ini;
        this.cpr = cpr;
        this.rolle = rolle;
        this.status = status;
    }

    public int getOprId() {
        return oprId;
    }

    public String getOprNavn() {
        return oprNavn;
    }

    public String getIni() {
        return ini;
    }

    public String getCpr() {
        return cpr;
    }

    public void setOprId(int oprId) {
        this.oprId = oprId;
    }

    public void setOprNavn(String oprNavn) {
        this.oprNavn = oprNavn;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


