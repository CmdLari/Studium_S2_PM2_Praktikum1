package scanner;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        Path wikiComicLocal = Paths.get("test.txt");
        var t = "file:///" + wikiComicLocal.toAbsolutePath();
        Scanner scanner = new Scanner(new URL(t).openStream(), StandardCharsets.UTF_8);


        scanner.useDelimiter("\\.");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
