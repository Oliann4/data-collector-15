import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ParsingJSON {
    private List<Station> stationsList;
    private String pathToFile;

    public ParsingJSON(String pathToFile) throws Exception {
        stationsList = new ArrayList<>();
        this.pathToFile = pathToFile;
        parsing();
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    private void parsing() throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(getJsonFile());

        jsonArray.forEach(jsonObject -> {
            JSONObject stationJsonObject = (JSONObject) jsonObject;

            Station station = new Station();
            station.setName((String) stationJsonObject.get("station_name"));

            String stringDepth = (String) stationJsonObject.get("depth");
            if (!stringDepth.equals("?")) {
                station.setDepth(parsingDouble(stringDepth));
            }

            stationsList.add(station);
        });
    }

    private double parsingDouble(String string) {
        int index = string.indexOf(',');
        String newString = index >= 0 ? string.replace(',', '.') : string;
        return Double.parseDouble(newString);
    }

    private String getJsonFile() throws IOException {
        String data = Files.readString(Paths.get(pathToFile));
        return data;
    }
}
