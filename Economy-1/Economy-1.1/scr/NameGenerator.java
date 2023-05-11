import java.util.*;
public class NameGenerator {
    
    public static ArrayList<String> firstNames = new ArrayList<String>(Arrays.asList("Noah", "Joe", "Joseph", "Franklin", "Dylan", "Luke",  "Johnny", "Oscar", "Jake", "Josh", "Hunter", "Blake", "Jeremiah", "Jarrett", "Jackson", "Ayden", "Sam", "Joel", "Matthew", "Dawson", "Austin", "Desi", "Danielle", "Kim", "Martin", "Savannah", "Darlene", "Kacey", "Kelly", "Connie", "Barbara", "Lindsey", "Nicole", "Payton", "Rylan", "Emily", "Vanessa", "Addison", "Daisy", "Taylor", "Dani", "Amber", "Brandy", "Sara", "Dixie", "Kathy", "Harly"));
    
    public static ArrayList<String> lastNames = new ArrayList<String>(Arrays.asList("Bourgeois", "Bowman", "Dotson", "Clarke", "Lemeke", "King", "Merrill", "Smith", "Segars", "Hill", "Washington", "Adams", "Obama", "Trump", "Bush", "Nixion", "Musk", "Moffit", "Jenson", "Stone", "Strange", "Coppersmith", "Potter", "White", "Black", "the Great"));
    
    public static String newName() {
        String randomFirst = firstNames.get((int)(Math.random() * firstNames.size()));
        String randomLast = lastNames.get((int)(Math.random() * lastNames.size()));
        return randomFirst + " " + randomLast;
    }
}
