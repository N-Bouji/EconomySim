package economy.Backend;

import java.util.*;
public class NameGenerator {

    // (175 Boy Names + 175 Girl Names) * 325 Last Names = 113,750 different names
    
    public static ArrayList<String> boyNames = new ArrayList<String>(Arrays.asList("A.J.", "Aaron", "Adam", "Aiden", "Alan", 
                                                                                        "Alex", "Alvin", "Amir", "Andrew", "Anthony", 
                                                                                        "Arthur", "Aryan", "Asher", "Ashton", "Atlas", 
                                                                                        "Austin", "Axel", "Ayden", "Barak", "Ben", 
                                                                                        "Benjamin", "Bennett", "Bjorn", "Blake", "Brayden", 

                                                                                        "Brian", "Brody", "C.J.", "Caleb", "Calvin", 
                                                                                        "Cameron", "Carlos", "Carson", "Cedric", "Charles", 
                                                                                        "Charles", "Christian", "Christopher", "Chuck", "Colton",
                                                                                        "Connor", "Connor", "Conrad", "Cooper", "Daerneth",
                                                                                        "Daniel", "Darrel", "Darren", "Davis", "Dawson",

                                                                                        "Declan", "Donald", "Dwight", "Dylan", "Edward",
                                                                                        "Eli", "Elias", "Elijah", "Eric", "Ethan",
                                                                                        "Eugene", "Evan", "Fabian", "Fabio", "Frank",
                                                                                        "Franklin", "Fransis", "Fred", "Fredrick", "Gabriel",
                                                                                        "Garet", "Gavin", "George", "Grayson", "Gunner",

                                                                                        "Guy", "Harold", "Harry", "Henry", "Homer",
                                                                                        "Hunter", "Ian", "Isaac", "Isaiah", "Israel",
                                                                                        "Ivan", "Jack", "Jackson", "Jacob", "Jake",
                                                                                        "James", "Jarrett", "Jason", "Jayce", "Jayden",
                                                                                        "Jeremiah", "Jerry", "Joe", "Joel", "John",

                                                                                        "Johnny", "Jonathan", "Jordan", "Joseph", "Josh",
                                                                                        "Julian", "Keith", "Kenneth", "Kevin", "Kyle",
                                                                                        "Landon", "Leo", "Leonardo", "Lex", "Liam",
                                                                                        "Logan", "Lorenzo", "Luca", "Lucas", "Luke",
                                                                                        "Matthew", "Mick", "Mike", "Miles", "Nate",

                                                                                        "Nathan", "Nic", "Nicholas", "Nick", "Noah",
                                                                                        "Nolan", "Oliver", "Oscar", "Owen", "Parker",
                                                                                        "Patrick", "Paul", "Peter", "Quentin", "R.J.",
                                                                                        "Richard", "Rick", "Rob", "Robert", "Roman",
                                                                                        "Ronald", "Roy", "Ryan", "Sam", "Samuel",

                                                                                        "Sean", "Sebastian", "Shawn", "Simon", "Stephen",
                                                                                        "Steve", "Steven", "Stone", "Theo", "Thomas",
                                                                                        "Tim", "Timmy", "Timothy", "Tom", "Tommy",
                                                                                        "Tucker", "Turner", "Vinvent", "Wesley", "Weston",
                                                                                        "Will", "William", "Wyatt", "Xavier", "Zeke"));
                                                                                        

    public static ArrayList<String> girlNames = new ArrayList<String>(Arrays.asList("Abigail", "Addison", "Alexandria", "Alice", "Allison",
                                                                                         "Alondra", "Amanda", "Amani", "Amber", "Amelia",
                                                                                         "Amy", "Andrea", "Angelina", "Angie", "Anna",
                                                                                         "Ariana", "Ashley", "Athena", "Audry", "Aurora",
                                                                                         "Autumn", "Ava", "Avery", "Barbara", "Beatrice",
                                                                                         
                                                                                         "Becky", "Bella", "Bethany", "Brandie", "Brandy",
                                                                                         "Brooke", "Brooklyn", "Callie", "Cataline", "Cecelia",
                                                                                         "Charlotte", "Chloe", "Chloe", "Christina", "Christy",
                                                                                         "Cindy", "Connie", "Cora", "Courtney", "Daisy",
                                                                                         "Daisy", "Dani", "Danielle", "Darlene", "Debbie",
                                                                                         
                                                                                         "Debora", "Delilah", "Desi", "Destiny", "Diana",
                                                                                         "Diane", "Dixie", "Eden", "Eleanor", "Elise",
                                                                                         "Eliza", "Elizabeth", "Ella", "Elly", "Emily",
                                                                                         "Emma", "Esmerelda", "Fallon", "Francine", "Georgia",
                                                                                         "Gianna", "Grace", "Hailey", "Hannah", "Harly",
                                                                                         
                                                                                         "Harper", "Hazel", "Irene", "Isabel", "Ivy",
                                                                                         "Jade", "Jennifer", "Jessica", "Joline", "Josie",
                                                                                         "Julia", "Julie", "June", "Kacey", "Kaitlyn",
                                                                                         "Karen", "Katalina", "Kathy", "Katie", "Katrina",
                                                                                         "Kaylee", "Kelly", "Kiera", "Kim", "Kimberly",
                                                                                         
                                                                                         "Lacy", "Laney", "Laura", "Lauren", "Leah",
                                                                                         "Lee", "Lila", "Lilian", "Lily", "Lindsey",
                                                                                         "Lucy", "Maci", "Madison", "Maggy", "Mandy",
                                                                                         "Margaret", "Maria", "Marie", "Mary", "Mattie",
                                                                                         "Maya", "Meridith", "Mia", "Millie", "Miranda",
                                                                                         
                                                                                         "Nala", "Nancy", "Naomi", "Natalie", "Natasha",
                                                                                         "Nicole", "Nikkie", "Nora", "Olivia", "Patricia",
                                                                                         "Payton", "Penny", "Phoebe", "Piper", "Quinn",
                                                                                         "Raven", "Reagan", "Rebeca", "Riley", "Rose",
                                                                                         "Rosie", "Ruth", "Rylan", "Sabrina", "Sadie",
                                                                                         
                                                                                         "Sally", "Samantha", "Sandra", "Sara", "Sarah",
                                                                                         "Savannah", "Scarlett", "Shirly", "Sky", "Sofia",
                                                                                         "Sophie", "Stephanie", "Susan", "Sydney", "Taylor",
                                                                                         "Tracy", "Vanessa", "Vicky", "Victoria", "Violet",
                                                                                         "Vivian", "Wendy", "Yolonda", "Zoe", "Zoey"));

    
    public static ArrayList<String> lastNames = new ArrayList<String>(Arrays.asList("Abignale", "Adams", "Ali", "Allred", "Anderson",
                                                                                         "Andrews", "Archer", "Austins", "Baker", "Banner",
                                                                                         "Bauers", "Bean", "Beasley", "Bell", "Bender",
                                                                                         "Benton", "Bernard", "Best", "Birch", "Black",
                                                                                         "Blasco", "Blevins", "Bond", "Booker", "Bourgeois",
                                                                                         
                                                                                         "Bowman", "Brock", "Brown", "Bruchet", "Bruner",
                                                                                         "Bryant", "Buffett", "Burr", "Bush", "Cain",
                                                                                         "Callahan", "Campbell", "Carel", "Carrol", "Carter",
                                                                                         "Carver", "Case", "Cavill", "Charles", "Cherry",
                                                                                         "Chevez", "Christiano", "Church", "Clark", "Clarke",
                                                                                         
                                                                                         "Clay", "Clemons", "Cleveland", "Clinton", "Cobb",
                                                                                         "Coffey", "Collins", "Combs", "Cook", "Cooper",
                                                                                         "Coppersmith", "Costa", "Crane", "Cruz", "Cuban",
                                                                                         "Cuprik", "Curry", "Danger", "Daniels", "Davis",
                                                                                         "Diaz", "Dickens", "Dickens", "Dotson", "Dudley",
                                                                                         
                                                                                         "Duffy", "Duke", "Dunlap", "Durant", "Dyer",
                                                                                         "Easter", "Edwards", "Einstein", "Epstien", "Euler",
                                                                                         "Farmer", "Fitzgerald", "Flores", "Ford", "Foreman",
                                                                                         "Franco", "Fry", "Frye", "Gallagher", "Garcia",
                                                                                         "Gates", "Glade", "Glasco", "Glass", "Gomez",
                                                                                         
                                                                                         "Gonzalez", "Goodman", "Goodwin", "Gossling", "Gould",
                                                                                         "Grande", "Graves", "Gray", "Green", "Grey",
                                                                                         "Griffin", "Hall", "Hammond", "Hampton", "Hanks",
                                                                                         "Harris", "Harvey", "Hayes", "Headrick", "Hendrix",
                                                                                         "Henry", "Hernandez", "Herrera", "Herring", "Hester",
                                                                                         
                                                                                         "Hill", "Hobbs", "Hogan", "Holland", "Hoover",
                                                                                         "Horne", "Hudson", "Hurley", "Irving", "Irwin",
                                                                                         "Jackson", "Jager", "James", "Jefferson", "Jenkins",
                                                                                         "Jenson", "Jobs", "Johnson", "Jones", "Jorgenson",
                                                                                         "Juarez", "Kane", "Keller", "Kennedy", "King",
                                                                                         
                                                                                         "Kirchoff", "Knight", "Kramer", "Lamb", "Landry",
                                                                                         "Lanier", "Ledger", "Lee", "Lei", "Lemeke",
                                                                                         "Lent", "Lewis", "Lincoln", "Lopez", "Lorrenz",
                                                                                         "Lucero", "Lynn", "Madden", "Maddox", "Marsh",
                                                                                         "Martinez", "Mathers", "Matthews", "Mayer", "McAurthor",
                                                                                         
                                                                                         "McBride", "McCann", "McDonald", "McGreggor", "McGuire",
                                                                                         "McKay", "Medina", "Mercado", "Mercury", "Merrill",
                                                                                         "Meruga", "Miller", "Mitchell", "Moffit", "Montague",
                                                                                         "Moore", "Morales", "Morgan", "Morris", "Moss",
                                                                                         "Mullen", "Musk", "Musk", "Nalley", "Nelson",
                                                                                         
                                                                                         "Nest", "Nixion", "Norris", "O'Brian", "O'Connor",
                                                                                         "O'Mally", "O'Neal", "Oak", "Oakly", "Obama",
                                                                                         "Oglethorpe", "Ortiz", "Osborne", "Parker", "Patel",
                                                                                         "Patterson", "Patton", "Peck", "Pelosi", "Perez",
                                                                                         "Perry", "Peters", "Peterson", "Phillips", "Poe",
                                                                                         
                                                                                         "Pope", "Poppins", "Porter", "Potter", "Potts",
                                                                                         "Powell", "Putin", "Quattlebaum", "Rae", "Reeves",
                                                                                         "Reid", "Richmond", "Rivera", "Rivers", "Roberts",
                                                                                         "Robinson", "Rodriguez", "Rogan", "Rogers", "Roland",
                                                                                         "Rollins", "Ross", "Rowe", "Rubio", "Salamanca",
                                                                                         
                                                                                         "Sampson", "Sanchez", "Sanders", "Sappington", "Schmitt",
                                                                                         "Segars", "Shepard", "Sherman", "Silva", "Silvers",
                                                                                         "Simons", "Simpson", "Sinclair", "Skinner", "Skywalker",
                                                                                         "Smith", "Snell", "Snell", "Snow", "Spence",
                                                                                         "Springer", "Squires", "Stanton", "Stark", "Steel",
                                                                                         
                                                                                         "Sterling", "Stevens", "Stewart", "Stokes", "Stone",
                                                                                         "Stone", "Stork", "Strange", "Taylor", "Temple",
                                                                                         "Tharpe", "Thompson", "Thorn", "Thorne", "Tribiani",
                                                                                         "Trump", "Twaine", "Tyson", "Upchurch", "Vader",
                                                                                         "Vance", "Ventura", "Vitale", "Von", "Walker",
                                                                                         
                                                                                         "Waller", "Ward", "Washington", "Waters", "Watson",
                                                                                         "Weasly", "Webb", "Wells", "West", "Wheeler",
                                                                                         "White", "Whitney", "Wiggs", "Wild", "Williams",
                                                                                         "Wilson", "Wimble", "Wisdom", "Wise", "Wright",
                                                                                         "Wyoming", "Xander", "Young", "Zohan", "the Great"));
    
    public static String newName(boolean isMan) {
        String randomFirst = "";
        if (isMan) {
            randomFirst = boyNames.get((int)(Math.random() * boyNames.size()));
        } else {
            randomFirst = girlNames.get((int)(Math.random() * girlNames.size()));
        }
        String randomLast = lastNames.get((int)(Math.random() * lastNames.size()));
        return randomFirst + " " + randomLast;
    }

    public static String newBoyFirstName() {
        return boyNames.get((int)(Math.random() * boyNames.size()));
    }

    public static String newGirlFirstName() {
        return girlNames.get((int)(Math.random() * girlNames.size()));
    }

    

    public static String newFirstName(boolean isMan) {
        if (isMan){
            return newBoyFirstName();
        } else {
            return newGirlFirstName();
        }

    }



    public static String getLastName(String name) {
        String[] names = name.split(" ");
        return names[1];
    }
    public static String getFirstName(String name) {
        String[] names = name.split(" ");
        return names[0];
    }



    public static void printSortedLastNames() {

        Collections.sort(lastNames);

        String lastNamesStr = "";

        for (int i = 0; i < lastNames.size(); i++) {

            if (i % 5 == 0) {
                lastNamesStr += "\n";
            }
            if (i % 25 == 0) {
                lastNamesStr += "\n";
            }

            lastNamesStr += '"' + lastNames.get(i) + '"' + ", ";

        }

        System.out.print(lastNamesStr);
    }

    public static void printSortedBoyNames() {
        Collections.sort(boyNames);

        String boyNamesStr = "";

        for (int i = 0; i < boyNames.size(); i++) {

            if (i % 5 == 0) {
                boyNamesStr += "\n";
            }
            if (i % 25 == 0) {
                boyNamesStr += "\n";
            }

            boyNamesStr += '"' + NameGenerator.boyNames.get(i) + '"' + ", ";

        }

        System.out.print(boyNamesStr);
    }

    public static void printSortedGirlNames() {
        Collections.sort(girlNames);

        String girlNamesStr = "";

        for (int i = 0; i < girlNames.size(); i++) {

            if (i % 5 == 0) {
                girlNamesStr += "\n";
            }
            if (i % 25 == 0) {
                girlNamesStr += "\n";
            }

            girlNamesStr += '"' + NameGenerator.girlNames.get(i) + '"' + ", ";

        }

        System.out.print(girlNamesStr);
    }
}
