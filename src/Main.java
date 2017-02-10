import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, ArrayList<Country>> hashMap = new HashMap();
    static ArrayList<Country> listOfAllCountries = new ArrayList<>();

    public static void scanFileIntoArray() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);

        while(fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[0], columns[1]);
            listOfAllCountries.add(country);
        }
        fileScanner.close();
    }

    public static void main(String[] args) throws Exception {

        scanFileIntoArray();

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

            for(Country country : listOfXCountries){
                growing = growing.concat(country.getName());
                growing = growing.concat(", ");
            }
            fw.write(growing.substring(0, (growing.length()-2)));
            fw.close();
        }
    }
}
