package beermanager;

public class Beer {
    private String name;
    private double alcoRate;
    private double price;
    private int rating;
    private int year;
    private String maunfacturer;
    private String country;
    private String path;

    public Beer(String name, double alcoRate, double price, int rating, int year, String maunfacturer, String country, String path) {
        this.name = name;
        this.alcoRate = alcoRate;
        this.price = price;
        this.rating = rating;
        this.year = year;
        this.maunfacturer = maunfacturer;
        this.country = country;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlcoRate() {
        return alcoRate;
    }

    public void setAlcoRate(double alcoRate) {
        this.alcoRate = alcoRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaunfacturer() {
        return maunfacturer;
    }

    public void setMaunfacturer(String maunfacturer) {
        this.maunfacturer = maunfacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String toCSV(){
        return getName()+","+getAlcoRate()+","+getPrice()+","+getRating()+","+getYear()+","+getMaunfacturer()+","+getCountry()+","+getPath();
    }
    
}

