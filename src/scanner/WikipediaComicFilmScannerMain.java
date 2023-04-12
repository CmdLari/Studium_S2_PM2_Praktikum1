package scanner;

import utils.YearInterval;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WikipediaComicFilmScannerMain {
	private static final String DIR_PREFIX = "";

	public static void main(String[] args) throws MalformedURLException, IOException {
		/*
		 * Wir arbeiten mit einer lokalen Datei / Kopie einer Wikipediaseite. 
		 * Referenz auf die Datei wird als URI String übergeben: file:///<absoluter Pfad zur Datei>
		 * Path wiki3DFilmLocal = Paths.get("Listevon3D-Filmen–Wikipedia.html") erzeugt einen relativen
		 * Pfad zur der Datei (Bezug das aktuelle Projekt)
		 * wiki3DFilmLocal.toAbsolutePath() erzeugt den absoluten Pfad, der als
		 * Argument übergeben wird.
		 */
		Path wikiComicLocal = Paths.get("Liste von Comicverfilmungen.html");
		ComicFilmScanner wp1 = new ComicFilmScanner("file:///" + wikiComicLocal.toAbsolutePath());
		//wp1.echoPage();

		/*
		 * Parsen der Seite und Einsammeln der Liste von 3D-Filmen eines Jahres / eines Zeitraums 
		 * in einem Verzeichnis (einer Java-Map).
		 */
		long start = System.currentTimeMillis();
		Map<String, Map<YearInterval,List<String>>> comicFilmMap = wp1.contentToComicMap();
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		ppMap(comicFilmMap);
	}
	
	private static <K extends Comparable<? super K>> void ppMap(Map<K, Map<YearInterval,List<String>>> aMap) {
		List<K> al = new ArrayList<K>(aMap.keySet());
		Collections.sort(al);
		for (K key : al) {
			System.out.printf("%s->", key);
			for (Map.Entry<YearInterval,List<String>> entry : aMap.get(key).entrySet()) {
				System.out.printf("%s %s ", entry.getKey(),entry.getValue().toString());
			}
			System.out.println();
		}
	}


}
