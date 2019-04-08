package beermanager;

import java.io.*;
import java.util.*;

public class UserDatabase {
    File file = new File("users.csv");
    PrintWriter output;
    Scanner input;  
    
    public ArrayList<User> readFromFile(){
        ArrayList<User> userList = new ArrayList<>();
        try{
            input = new Scanner(file);
            while(input.hasNextLine()){
                String record = input.nextLine();
                String[] fields = record.split(",");
                String username= fields[0];
                String password = fields[1];
                User user = new User(username, password);
                userList.add(user);
            }
        }catch(FileNotFoundException e){
            System.out.println("Error:"+e);
        }finally{
            return userList;
        }
    }
    
    public void createUser(User user){
        ArrayList<User> userList = new ArrayList<>();
        userList = readFromFile();
        try{
            output = new PrintWriter(file);
            userList.add(user);
            for(int i = 0; i < userList.size(); i++){
                    output.println(userList.get(i).toCSV());
                }
        }catch(FileNotFoundException e){
            System.out.println("Exception: "+e);
        }finally{
           if(output != null)
                output.close();         
        }
    }
    
    public boolean checkUser(String uName, String pWord){
        ArrayList<User> userList = readFromFile();        
        boolean b = false;
        
        for(int i = 0; i < userList.size(); i++){
            if(uName.equalsIgnoreCase(userList.get(i).getUsername()) && pWord.equals(userList.get(i).getPassword())){
                b = true;
            }
        }
        return b;
    }
    
}
