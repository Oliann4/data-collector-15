import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileBuilderMap {
    private List<Line> linesList;
    private List<Station> stationList;
    private List<Connections> connectionsList;
    private final String PATH_MAP_FILE = "output/map.json";

    public FileBuilderMap(List<Line> linesList,
                          List<Station> stationList,
                          List<Connections> connectionsList)
            throws IOException {
        this.linesList = linesList;
        this.stationList = stationList;
        this.connectionsList = connectionsList;
        createFile();
    }

    private void createFile() throws IOException {
        Files.writeString(Paths.get(PATH_MAP_FILE),
                buildJson().toString(),
                StandardOpenOption.CREATE);
    }

    private JSONObject buildJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lines", buildLines());
        jsonObject.put("stations", buildStations());
        jsonObject.put("connections", buildConnections());
        return jsonObject;
    }

    private JSONArray buildLines() {
        JSONArray linesJsonArray = new JSONArray();

        linesList.forEach(line -> {
            JSONObject object = new JSONObject();
            object.put("number", line.getNumber());
            object.put("name", line.getName());

            linesJsonArray.add(object);
        });

        return linesJsonArray;
    }

    private JSONObject buildStations() {
        JSONObject stationsJsonObject = new JSONObject();

        linesList.forEach(line -> {
            List<String> stationsOnLine = new ArrayList<>();
            stationList.forEach(station -> {
                if (line.getNumber().equals(station.getLine())) {
                    stationsOnLine.add(station.getName());
                }
            });
            stationsJsonObject.put(line.getNumber(), stationsOnLine);
        });

        return stationsJsonObject;
    }

    private JSONArray buildConnections() {
        JSONArray connectionsJsonArray = new JSONArray();
        connectionsList.forEach(connection -> {
            JSONArray jsonArray = new JSONArray();
            connection.getStationList().forEach(station -> {
                JSONObject object = new JSONObject();
                object.put("line", station.getLine());
                object.put("station", station.getName());
                jsonArray.add(object);
            });
            connectionsJsonArray.add(jsonArray);
        });
        return connectionsJsonArray;
    }
}