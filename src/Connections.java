import java.util.ArrayList;
import java.util.List;

public class Connections {
    List<Station> stationList;

    public Connections() {
        stationList = new ArrayList<>();
    }

    public void addStation(Station station) {
        stationList.add(station);
    }

    public List<Station> getStationList() {
        return stationList;
    }

    @Override
    public String toString() {
        return "Connections{" + stationList +
                '}';
    }
}
