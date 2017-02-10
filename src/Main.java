import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, ArrayList<Country>> hashMap = new HashMap();
    static ArrayList<Country> listOfAllCountries = new ArrayList<>();

    public static void main(String[] args) throws Exception {

    //read countries.txt and parse into HashMap
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);

        //puts all countries into an arraylist
        while(fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[0], columns[1]);
            listOfAllCountries.add(country);
        }


        //asks user to type  single letter, throws exception if not single letter
        System.out.println("Please enter a single letter:");
        Scanner letterScanner = new Scanner(System.in);
        String userEntered = letterScanner.nextLine();
        letterScanner.close();

        ArrayList<Country> listOfXCountries = new ArrayList<>();

        if(userEntered.length() != 1){
            throw new Exception("Single letter error.");
        }
        else if(userEntered.length() == 1){
            for (Country country : listOfAllCountries){
                if(userEntered.equalsIgnoreCase(country.getName().substring(0,1))){
                    listOfXCountries.add(country);
                }
            }
            hashMap.put(userEntered, listOfXCountries);

            File g = new File(userEntered + "_countries.txt");
            FileWriter fw = new FileWriter(g);
            String growing = "";
            int i = 0;
            for(Country country : listOfXCountries){
                String aCountryName = listOfXCountries.get(i).getName();
                growing = growing.concat(aCountryName);
                growing = growing.concat(", ");
                i++;
            }
            fw.write(growing.substring(0, (growing.length()-2)));
            fw.close();
        }
        fileScanner.close();

    }
}
