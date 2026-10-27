package cl.bennu.controljornadabackend.model;

public class Commune {

    private Integer id;
    private Integer cityId;
    private String name;
    private String siiCode;
    private String tgrCode;

    public Commune() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiiCode() {
        return siiCode;
    }

    public void setSiiCode(String siiCode) {
        this.siiCode = siiCode;
    }

    public String getTgrCode() {
        return tgrCode;
    }

    public void setTgrCode(String tgrCode) {
        this.tgrCode = tgrCode;
    }
}