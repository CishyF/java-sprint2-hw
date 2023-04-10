import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileReader {

    public static final CSVFileReader INSTANCE = new CSVFileReader();

    private CSVFileReader() {}

    public List<List<String>> readCSVFileByRows(String path) {

        List<List<String>> separatedRows = new ArrayList<>();

        try {
            List<String> rows = Files.readAllLines(Path.of(path));

            rows.stream().map(r -> Arrays.asList(r.split(","))).forEach(separatedRows::add);
        } catch (IOException e) {
            throw new RuntimeException("Проблема со считыванием!");
        }

        return separatedRows;
    }



}
