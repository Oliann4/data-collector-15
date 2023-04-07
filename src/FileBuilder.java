import java.io.IOException;
import java.util.List;

public class FileBuilder {

    public FileBuilder(List<Line> linesList,
                       List<Station> stationList,
                       List<Connections> connectionsList)
            throws IOException {
        new FileBuilderStations(stationList);
        new FileBuilderMap(linesList, stationList, connectionsList);
    }
}
