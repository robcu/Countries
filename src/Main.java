import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static HashMap<String, ArrayList<Country>> Atlas = new HashMap();
    static ArrayList<Country> listOfCountries = new ArrayList<>();

    public static void scanFileIntoHashMap() throws FileNotFoundException {

        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);

        String firstLetter = "";
        String lastFirstLetter = "a";
        Country country = new Country();

        while(fileScanner.hasNext()){
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");

            firstLetter = columns[1].substring(0,1);

            if(firstLetter.equalsIgnoreCase(lastFirstLetter)){
                country = new Country(columns[0], columns[1]);
                listOfCountries.add(country);
            }
            else if (!(firstLetter.equalsIgnoreCase(lastFirstLetter))){
                Atlas.put(lastFirstLetter, listOfCountries);

                listOfCountries = new ArrayList<>();
                //listOfCountries.clear();

                country = new Country(columns[0], columns[1]);
                listOfCountries.add(country);
            }
            lastFirstLetter = firstLetter;
        }
        listOfCountries.add(country);
        Atlas.put(lastFirstLetter, listOfCountries);

        fileScanner.close();
    }

    public static void main(String[] args) throws Exception {

        scanFileIntoHashMap();

        System.out.println("Please enter a single letter:");
        Scanner letterScanner = new Scanner(System.in);
        String userEntered = letterScanner.nextLine();
        letterScanner.close();

        if(userEntered.length() != 1){
            throw new Exception("Single letter error.");
        }
        else if(userEntered.length() == 1){
            File g = new File(userEntered + "_countries.txt");
            FileWriter fw = new FileWriter(g);
            String growing = "";


            ArrayList<Country> temp = Atlas.get(userEntered);
            for(Country country : temp){
                growing = growing.concat(country.getName());
                growing = growing.concat(", ");
            }
            fw.write(growing.substring(0, (growing.length()-2)));
            fw.close();
        }
    }
}
