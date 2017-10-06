package nameRetriever;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Retriever {
	
	Scanner scanner = new Scanner(System.in);
	boolean found = false;

	public Retriever() {
		System.out.println("Enter an email id: ");
		String input = scanner.nextLine();
		String address = "http://www.ecs.soton.ac.uk/people/" + input;
		try {
			URL url = new URL(address);
			
			BufferedReader html = new BufferedReader(
					new InputStreamReader(url.openStream()));
			String htmlLine;
			String searchText = "\"name\">";
			while ((htmlLine = html.readLine()) != null) {
				int nameIndex = htmlLine.indexOf(searchText);
				if(nameIndex != -1){
					String shortenedString = htmlLine.substring(nameIndex + searchText.length(), htmlLine.length());
					shortenedString = shortenedString.substring(0, shortenedString.indexOf("</h1>"));
					System.out.println(shortenedString);
					found = true;
				}
			}
			html.close();
			if(!found) {
				System.out.println("No such id!");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
