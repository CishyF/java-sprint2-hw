import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CSVFileReader {

    private CSVFileReader() {}

    public static List<String> readCSVFileByRows(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("Проблема со считыванием данных из файла!");
        }
    }



}
