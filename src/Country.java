/**
 * Created by robculclasure on 2/9/17.
 */
public class Country {
    String name;
    String abbrev;

    public Country(){}

    public Country(String abbrev, String name){
        this.name = name;
        this.abbrev = abbrev;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
}
