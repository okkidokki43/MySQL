package objects;

public class Curator {
    private long id;
    private String fio;

    @Override
    public String toString() {
        return "Curators{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                '}';
    }
    public Curator(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }
    public long geIid() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFio() {
        return fio;
    }
    public void setFio(String fio) {
        this.fio = fio;
    }
}