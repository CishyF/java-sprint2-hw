import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {

    public static final CSVFileReader INSTANCE = new CSVFileReader();

    private CSVFileReader() {}

    public List<String[]> readCSVFileByRowsAndColumns(String path) {

        List<String[]> splitRows = new ArrayList<>();

        try {
            List<String> rows = Files.readAllLines(Path.of(path));

            rows.stream().map(row -> row.split(",")).forEach(splitRows::add);
        } catch (IOException e) {
            throw new RuntimeException("Проблема со считыванием данных из файла!");
        }

        return splitRows;
    }



}
