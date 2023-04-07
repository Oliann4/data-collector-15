import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileBuilderStations {
    private List<Station> stationList;
    private final String PATH_STATIONS_FILE = "output/stations.json";

    public FileBuilderStations(List<Station> stationList) throws IOException {
        this.stationList = stationList;
        createFile();
    }

    private void createFile() throws IOException {
        Files.writeString(Paths.get(PATH_STATIONS_FILE),
                buildJson().toString(),
                StandardOpenOption.CREATE);
    }

    private JSONObject buildJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("stations", buildStations());
        return jsonObject;
    }

    private JSONArray buildStations() {
        JSONArray stationsJsonArray = new JSONArray();

        stationList.forEach(station -> {
            JSONObject object = new JSONObject();
            object.put("name", station.getName());
            object.put("line", station.getLine());
            if (!(station.getDate() == null)) {
                DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");
                object.put("date", station.getDate().format(formatter).toString());
            }
            if (!(station.getDepth() == null)) {
                object.put("depth", station.getDepth().toString());
            }
            object.put("hasConnection", station.getHasConnection().toString());

            stationsJsonArray.add(object);
        });

        return stationsJsonArray;
    }
}
