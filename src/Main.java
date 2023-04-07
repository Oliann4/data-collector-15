import java.util.List;

public class Main {
    private static List<Line> linesList;
    private static List<Station> stationList;
    private static List<Connections> connectionsList;

    public static void main(String[] args) {
        String TYPE_JSON = ".json";
        String TYPE_CSV = ".csv";

        String pathToHTML = "https://skillbox-java.github.io/";
        String pathToDirectory = "data";

        try {
            ParsingHTML parsingHTML = new ParsingHTML(pathToHTML);
            linesList = parsingHTML.getLinesList();
            stationList = parsingHTML.getStationsList();
            connectionsList = parsingHTML.getConnectionsList();

            FileSearch fileSearch = new FileSearch(pathToDirectory);
            List<String> fileList = fileSearch.getFileList();

            for (int i = 0; i < fileList.size(); i++) {

                if (fileList.get(i).endsWith(TYPE_JSON)) {
                    ParsingJSON parsingJSON = new ParsingJSON(fileList.get(i));
                    List<Station> stationListFromJSON = parsingJSON.getStationsList();
                    unionByDepth(stationListFromJSON);
                }

                if (fileList.get(i).endsWith(TYPE_CSV)) {
                    ParsingCSV parsingCSV = new ParsingCSV(fileList.get(i));
                    List<Station> stationListFromCSV = parsingCSV.getStationsList();
                    unionByDate(stationListFromCSV);
                }
            }

            new FileBuilder(linesList, stationList, connectionsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unionByDepth(List<Station> stationList1) {
        stationList1.forEach(station1 -> {
            String name1 = station1.getName();
            stationList.forEach(station -> {
                if (station.getName().equals(name1)) {
                    station.setDepth(station1.getDepth());
                }
            });
        });
    }

    private static void unionByDate(List<Station> stationList1) {
        stationList1.forEach(station1 -> {
            String name1 = station1.getName();
            stationList.forEach(station -> {
                if (station.getName().equals(name1)) {
                    station.setDate(station1.getDate());
                }
            });
        });
    }
}