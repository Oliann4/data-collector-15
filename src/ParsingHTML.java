import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsingHTML {
    private List<Line> linesList;
    private List<Station> stationsList;
    private List<Connections> connectionsList;

    public ParsingHTML(String path) throws IOException {
        linesList = new ArrayList<>();
        stationsList = new ArrayList<>();
        connectionsList = new ArrayList<>();
        parsing(Jsoup.connect(path).get());
    }

    public List<Line> getLinesList() {
        return linesList;
    }

    public List<Station> getStationsList() {
        return stationsList;
    }

    public List<Connections> getConnectionsList() {
        return connectionsList;
    }

    private void parsing(Document document) {
        parsingLines(document);
        parsingStations(document);
        parsingConnections(document);
    }

    private void parsingLines(Document document) {
        Elements lines = document.select("span.js-metro-line");
        lines.forEach(lineElement -> {
            Line line = new Line();
            line.setNumber(lineElement.attr("data-line"));
            line.setName(lineElement.text());
            linesList.add(line);
        });
    }

    private void parsingStations(Document document) {
        Elements stationsOnLine = document.select("div.js-metro-stations");

        for (int i = 0; i < stationsOnLine.size(); i++) {
            Elements stations = stationsOnLine.get(i).select("p.single-station");

            for (Element stationElement : stations) {
                Station station = new Station();
                station.setName(stationElement.select("span.name").text());
                station.setLine(linesList.get(i).getNumber());
                station.setHasConnection(stationElement.select("span.t-icon-metroln").isEmpty() ?
                        false : true);
                stationsList.add(station);
            }
        }
    }

    private void parsingConnections(Document document) {
        Elements stationsOnLine = document.select("div.js-metro-stations");

        for (int i = 0; i < stationsOnLine.size(); i++) {
            Elements stations = stationsOnLine.get(i).select("p.single-station");

            for (Element stationElement : stations) {
                Elements connectionElements = stationElement.select("span.t-icon-metroln");

                if (!connectionElements.isEmpty()) {
                    Connections connection = new Connections();

                    String stationName = stationElement.select("span.name").text();
                    String stationLine = linesList.get(i).getNumber();
                    connection.addStation(searchStation(stationName, stationLine));

                    for (Element element : connectionElements) {
                        connection.addStation(getConnectionStation(element));
                    }

                    addConnection(connection);
                }
            }
        }
    }

    private Station searchStation(String name, String line) {
        for (Station station : stationsList) {
            if (station.getName().equals(name) &&
                    station.getLine().equals(line)) {
                return station;
            }
        }
        return null;
    }

    private Station getConnectionStation(Element element) {
        String titleElement = element.attr("title");
        String stationName = getStationName(titleElement);

        String classElement = element.attr("class");
        String stationLine = getStationLine(classElement);

        return searchStation(stationName, stationLine);
    }

    private String getStationName(String text) {
        String stationName = "";
        Pattern pattern = Pattern.compile("«([^»]+)»");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            stationName = matcher.group(1);
        }
        return stationName;
    }

    private String getStationLine(String text) {
        String regex = "t-icon-metroln ln-";
        return text.replace(regex, "");
    }

    private void addConnection(Connections connection1) {
        if (connectionsList.isEmpty()) {
            connectionsList.add(connection1);
        }

        boolean containsBoolean = false;
        for (Connections connection : connectionsList) {
            for (Station station : connection.getStationList()) {
                if (connection1.getStationList().contains(station)) {
                    containsBoolean = true;
                    break;
                }
            }
        }
        if(!containsBoolean) {
            connectionsList.add(connection1);
        }
    }
}
