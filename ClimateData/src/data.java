import java.util.ArrayList;
import java.util.List;

public class data {
	public String city; //Aberporth
	public String locationE; // 224100E
	public String locationN; // 252100N
	public double locationLat; // Lat 52139
	public double locationLon; // Lon -4.579
	public int locationMetres; // 62
	
	public List<Integer> year = new ArrayList<Integer>();
	public List<Integer> month = new ArrayList<Integer>();
	public List<Double> min_temp = new ArrayList<Double>();
	public List<Double> max_temp = new ArrayList<Double>();
	public List<Integer> frosty_days = new ArrayList<Integer>();
	public List<Double> rain_in_mm = new ArrayList<Double>();
	public List<Double> hours_of_sun = new ArrayList<Double>();
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLocationE() {
		return locationE;
	}
	public void setLocationE(String locationE) {
		this.locationE = locationE;
	}
	public String getLocationN() {
		return locationN;
	}
	public void setLocationN(String locationN) {
		this.locationN = locationN;
	}
	public double getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}
	public double getLocationLon() {
		return locationLon;
	}
	public void setLocationLon(double locationLon) {
		this.locationLon = locationLon;
	}
	public int getLocationMetres() {
		return locationMetres;
	}
	public void setLocationMetres(int locationMetres) {
		this.locationMetres = locationMetres;
	}
	public List<Integer> getYear() {
		return year;
	}
	public void setYear(List<Integer> year) {
		this.year = year;
	}
	public List<Integer> getMonth() {
		return month;
	}
	public void setMonth(List<Integer> month) {
		this.month = month;
	}
	public List<Double> getMin_temp() {
		return min_temp;
	}
	public void setMin_temp(List<Double> min_temp) {
		this.min_temp = min_temp;
	}
	public List<Double> getMax_temp() {
		return max_temp;
	}
	public void setMax_temp(List<Double> max_temp) {
		this.max_temp = max_temp;
	}
	public List<Integer> getFrosty_days() {
		return frosty_days;
	}
	public void setFrosty_days(List<Integer> frosty_days) {
		this.frosty_days = frosty_days;
	}
	public List<Double> getRain_in_mm() {
		return rain_in_mm;
	}
	public void setRain_in_mm(List<Double> rain_in_mm) {
		this.rain_in_mm = rain_in_mm;
	}
	public List<Double> getHours_of_sun() {
		return hours_of_sun;
	}
	public void setHours_of_sun(List<Double> hours_of_sun) {
		this.hours_of_sun = hours_of_sun;
	}

}
