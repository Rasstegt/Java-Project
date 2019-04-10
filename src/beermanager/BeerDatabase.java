package beermanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BeerDatabase {
    File file = new File("beer.csv");
    PrintWriter output;
    Scanner input;  
    
    public ObservableList<Beer> readFromFile(){
        ObservableList<Beer> beerList = FXCollections.observableArrayList();
        try{
            input = new Scanner(file);
            while(input.hasNextLine()){
                String record = input.nextLine();
                String[] fields = record.split(",");
                String name = fields[0];
                double aRate = Double.parseDouble(fields[1]);
                double price = Double.parseDouble(fields[2]);
                int rating = Integer.parseInt(fields[3]);
                int year = Integer.parseInt(fields[4]);
                String manufacturer = fields[5];
                String country = fields[6];
                String path = fields[7];
                Beer beer = new Beer(name,aRate,price,rating,year,manufacturer,country, path);
                beerList.add(beer);
            }
        }catch(FileNotFoundException e){
            System.out.println("Error:"+e);
        }finally{
            return beerList;
        }
    }
    
    public void createBeer(Beer beer){
        ObservableList<Beer> beerList = FXCollections.observableArrayList();
        beerList = readFromFile();
        try {
            output = new PrintWriter(file);
            beerList.add(beer);
            for(int i = 0; i < beerList.size(); i++) {
                output.println(beerList.get(i).toCSV());
            }
        }catch(FileNotFoundException e){
            System.out.println("Exception: "+e);
        }finally{
           if(output != null)
                output.close();         
        }
    }

    public void deleteFromFile(Beer beer){
        ObservableList<Beer> beerList = FXCollections.observableArrayList();
        beerList = readFromFile();
        try{
            output = new PrintWriter(file);
            for(int i = 0; i < beerList.size(); i++){
                if(beer.getName().equals(beerList.get(i).getName())){
                    beerList.remove(i);
                    for(int j = 0; j < beerList.size(); j++){
                        output.println(beerList.get(j).toCSV());
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Error:"+e);
        }finally{
           if(output != null)
                output.close();         
        }
    }
    
    public void editFile(Beer beer,String name, double alcoRate, double price, 
        int rating, int year, String maunfacturer, String country, String path){
        ObservableList<Beer> beerList = readFromFile();
        for(int i = 1; i < beerList.size(); i++){
            if(beer.getName().equals(beerList.get(i).getName())){
                beerList.get(i).setName(name);
                beerList.get(i).setAlcoRate(alcoRate);
                beerList.get(i).setPrice(price);
                beerList.get(i).setRating(rating);
                beerList.get(i).setYear(year);
                beerList.get(i).setMaunfacturer(maunfacturer);
                beerList.get(i).setCountry(country);
                beerList.get(i).setPath(path);
            }
        }
        try{
            output = new PrintWriter(file);
            for(int i = 0; i < beerList.size(); i++){
                    output.println(beerList.get(i).toCSV());
                }
        }catch(FileNotFoundException e){
            System.out.println("Exception: "+e);
        }finally{
           if(output != null)
                output.close();         
        }
    }
    
    
    
}
