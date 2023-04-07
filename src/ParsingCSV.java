import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParsingCSV {
    private List<Station> stationsList;
    private String pathToFile;

    public ParsingCSV(String pathToFile) throws IOException {
        stationsList = new ArrayList<>();
        this.pathToFile = pathToFile;
        parsing();
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    private void parsing() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(pathToFile));
        for (int i = 1; i < lines.size(); i++) {
            String[] strings = lines.get(i).split(",");

            Station station = new Station();
            station.setName(strings[0]);
            station.setDate(parsingDate(strings[1]));

            stationsList.add(station);
        }
    }

    private LocalDate parsingDate(String string) {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(string, formatter);
    }
}
