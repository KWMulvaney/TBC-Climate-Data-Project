import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Weather {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<data> data = new ArrayList<data>();

	public static void main(String[] args) throws IOException {
		String option = "";
		Weather main = new Weather();
		
		System.out.println("Loading data...");
		main.loadData();

		System.out.println("Welcome to the Weather application!");
		System.out.println("");
		System.out.println("Please select an option below: ");

		do {
			System.out.println("------------------------------------------------------------------------");
			System.out.println("1. List all locations");
			System.out.println("2. List all weather observations for a location");
			System.out.println("3. List weather observations for a given month / year");
			System.out.println("4. Find min and max recorded temperature for a given location");
			System.out.println("5. Find location with maximum rainfall / hours of sun / temperature");
			System.out.println("6. Find location with minimum rainfall / hours of sun / temperatures");
			System.out.println("7. List location, oldest first");
			System.out.println("Q. Quit");
			System.out.println("------------------------------------------------------------------------");
			option = scanner.nextLine();

			switch (option) {
			case "1":
				System.out.println("List of all locations:");
				listAllLocations();
				break;
			case "2":
				System.out.println("List of all locations: ");
				listAllLocations();
				System.out.println("--------------------------");
				System.out.println("Please select a location by entering its corresponding number.");
				int location = Integer.parseInt(scanner.nextLine());
				listWeatherObservation((location-1));
				break;
			case "3":
				System.out.println("Enter year:");
				int year = Integer.parseInt(scanner.nextLine());
				System.out.println("Enter month:");
				int month = Integer.parseInt(scanner.nextLine());
				listYearMonthWeather(year, month);
				break;
			case "4":
				System.out.println("List of all locations: ");
				listAllLocations();
				System.out.println("--------------------------");
				System.out.println("Please select a location by entering its corresponding number.");
				int minMaxLocation = Integer.parseInt(scanner.nextLine());
				findMinMaxLocation(minMaxLocation);
				break;
			case "5":

				break;
			case "6":

				break;
			}
		} while (!option.equalsIgnoreCase("Q"));
	}

	// Formats link to <location>data.txt
	public static String formatLocation(String location) {
		String fileName = "";
		fileName = location.replaceAll("\\s+", "");

		fileName += "data.txt";

		fileName = new String(fileName).toLowerCase();
		return fileName;
	}

	public void loadData() throws IOException {
		URL weatherLocation = null;
		Scanner file = null;
		Scanner locations = new Scanner(new File("locations.txt"));
		String line = "";

		// Looping each location
		while (locations.hasNext()) {
			List<Integer> year = new ArrayList<Integer>();
			List<Integer> month = new ArrayList<Integer>();
			List<Double> min_temp = new ArrayList<Double>();
			List<Double> max_temp = new ArrayList<Double>();
			List<Integer> frosty_days = new ArrayList<Integer>();
			List<Double> rain_in_mm = new ArrayList<Double>();
			List<Double> hours_of_sun = new ArrayList<Double>();

			weatherLocation = new URL("https://www.metoffice.gov.uk/pub/data/weather/uk/climate/stationdata/"
					+ formatLocation(locations.nextLine()));
			file = new Scanner(weatherLocation.openStream());
			// Adds new weather data for a city
			data weather = new data();
			int lineCounter = 0;

			// Looping each line in location
			while (file.hasNext()) {
				line = file.nextLine();
				// For summary of weather
				switch (lineCounter) {
				case 0:
					String[] tempLocationHolder = line.split("\\s"); // This gets the first word of the location.
					weather.setCity(tempLocationHolder[0]);
					break;
				case 1:
					String[] locationInformation = line.split("\\s");

					weather.setLocationE(locationInformation[1]);
					weather.setLocationN(locationInformation[2].replaceAll(",", ""));

					for (int j = 0; j < locationInformation.length; j++) {
						if (locationInformation[j].equalsIgnoreCase("Lat")) {
							weather.setLocationLat(Double.parseDouble(locationInformation[j + 1]));
						} else if (locationInformation[j].equalsIgnoreCase("Lon")) {
							weather.setLocationLon(Double.parseDouble(locationInformation[j + 1].replaceAll(",", "")));
						} else if (locationInformation[j].equalsIgnoreCase("metres")) {
							weather.setLocationMetres(Integer.parseInt(locationInformation[j - 1]));
						}
					}
					break;
				}

				// Sets lineCounter to 9 when degC is found (the last line
				// before weather data)
				if (line.indexOf("degC") >= 0) {
					lineCounter = 9;
				}

				// Checks to see if line counter is 10 - This means weather data
				// starts.
				
				String[] lineInformation = new String[8];
				
				try {
					if (lineCounter >= 10) {
						line = line.replace("*", "");
						line = line.replace("#", "");
						
						lineInformation = line.split("\\s+");
						
						if(lineInformation.length > 7) {

							if (lineInformation[1].indexOf("---") >= 0 || lineInformation[1].equals("-")) {
								year.add(-9999);
							} else {
								year.add(Integer.parseInt(lineInformation[1]));
							}

							if (lineInformation[2].indexOf("---") >= 0 || lineInformation[2].equals("-")) {
								month.add(-9999);
							} else {
								month.add(Integer.parseInt(lineInformation[2]));
							}

							if (lineInformation[3].indexOf("---") >= 0 || lineInformation[3].equals("-")) {
								max_temp.add(Double.NaN);
							} else {
								max_temp.add(Double.parseDouble(lineInformation[3].replaceAll("[^\\d.]", "")));
							}

							if (lineInformation[4].indexOf("---") >= 0 || lineInformation[4].equals("-")) {
								min_temp.add(Double.NaN);
							} else {
								min_temp.add(Double.parseDouble(lineInformation[4].replaceAll("[^\\d.]", "")));
							}

							if (lineInformation[5].indexOf("---") >= 0 || lineInformation[5].equals("-")) {
								frosty_days.add(-9999);
							} else {
								frosty_days.add(Integer.parseInt(lineInformation[5]));
							}

							if (lineInformation[6].indexOf("---") >= 0 || lineInformation[6].equals("-")) {
								rain_in_mm.add(Double.NaN);
							} else {
								rain_in_mm.add(Double.parseDouble(lineInformation[6]));
							}

							if (lineInformation[7].indexOf("---") >= 0 || lineInformation[7].equals("-")) {
								hours_of_sun.add(Double.NaN);
							} else {
								hours_of_sun.add(Double.parseDouble(lineInformation[7].replaceAll("[^\\d.]", "")));
							}
						}
					}
				} catch (Exception e) {
					System.out.println(weather.getCity());
					System.out.println(lineInformation.length);
					System.out.println(e.getMessage());
				}

				lineCounter++;
			}

			// Adds all weather data
			weather.setYear(year);
			weather.setMonth(month);
			weather.setMax_temp(max_temp);
			weather.setMin_temp(min_temp);
			weather.setFrosty_days(frosty_days);
			weather.setRain_in_mm(rain_in_mm);
			weather.setHours_of_sun(hours_of_sun);

			data.add(weather);
			lineCounter = 0;
		}
		locations.close();
	}

	public static void listWeatherData(int location, int weather) {
		System.out.println("Year: " + data.get(location).getYear().get(weather).toString().replaceAll("-9999", "---") + ", Month: "
				+ data.get(location).getMonth().get(weather).toString().replaceAll("-9999", "---") + ", Max Degree: "
				+ data.get(location).getMax_temp().get(weather).toString().replaceAll("NaN", "---") + ", Min Degree: "
				+ data.get(location).getMin_temp().get(weather).toString().replaceAll("NaN", "---") + ", Frosty Days: "
				+ data.get(location).getFrosty_days().get(weather).toString().replaceAll("-9999", "---") + ", Rain in MM: "
				+ data.get(location).getRain_in_mm().get(weather).toString().replaceAll("NaN", "---") + ", Sun Hours: "
				+ data.get(location).getHours_of_sun().get(weather).toString().replaceAll("NaN", "---"));
	}

	// Menu selection 1
	public static void listAllLocations() {
		for (int i = 0; i < data.size(); i++) {
			System.out.println((i + 1) + ". " + data.get(i).getCity());
		}
	}

	// Menu selection 2
	public static void listWeatherObservation(int location) throws IOException {
		boolean foundLocation = false;

		try {
			foundLocation = true;
			for (int j = 0; j < data.get(location).getYear().size(); j++) {
				listWeatherData(location, j);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (!foundLocation) {
			System.out.println("Error: Location not found! Please enter same location as listed on option 1.");
		}

	}
	
	// Menu selection 3
	public static void listYearMonthWeather(int year, int month) {
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).getYear().size(); j++) {
				if(data.get(i).getYear().get(j).equals(year) && data.get(i).getMonth().get(j).equals(month)) {
					System.out.println("~~");
					System.out.println("City: " + data.get(i).getCity());
					listWeatherData(i, j);
				}
			}
		}
	}

	// Menu selection 4
	public static void findMinMaxLocation(int location) {
			for (int i = 0; i < data.get(location).getMax_temp().size(); i++) {
				System.out.println("Location: " + data.get(location).getCity() + ", Max Temp: " + 
			data.get(location).getMax_temp().get(i).toString().replaceAll("NaN", "---") 
				+ " Min Temp: " + data.get(location).getMin_temp().get(i).toString().replaceAll("NaN", "---") + 
				", Recorded on: " + data.get(location).getYear().get(i).toString().replaceAll("-9999", "---") + 
				"/" + data.get(location).getMonth().get(i).toString().replaceAll("-9999", "---"));
			}
	}
	
}