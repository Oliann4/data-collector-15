import java.time.LocalDate;

public class Station {
    private String name;
    private String line;
    private LocalDate date;
    private Double depth;
    private Boolean hasConnection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", date=" + date +
                ", depth=" + depth +
                ", hasConnection=" + hasConnection +
                '}';
    }
}
