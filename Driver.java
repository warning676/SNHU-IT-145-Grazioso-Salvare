
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	
	// -------------------------------------------------------------------------------- //
	// ---------------------------- 1. ANIMAL DATA LISTS ------------------------------ //
	// -------------------------------------------------------------------------------- //

	/**
	 * Lists holding all dogs and monkeys currently managed by the system
	 * These are the master lists for all operations (intake, reserve, report)
	 */
    private static ArrayList<Dog> dogList = new ArrayList<Dog>();
    private static ArrayList<Monkey> monkeyList = new ArrayList<Monkey>();
    
    // -------------------------------------------------------------------------------- //
 	// ----------------------------- 2. ELIGIBILITY LISTS ----------------------------- //
 	// -------------------------------------------------------------------------------- //
    
    /**
     * Array defining the specific monkey species eligible for intake
     * Intake of species outside this list is prevented
     */
    private static String[] eligibleSpeciesList = {"Capuchin", "Guenon", "Macaque", "Marmoset", "Squirrel", "Tamarin"};
    
    /**
     * Array defining all valid training statuses for both dogs and monkeys
     * Used for validating user input during intake and editing
     */
    private static String[] eligibleTrainingStatusList = {"Intake", "Phase I", "Phase II", "Phase III", "Phase IV", "Phase V", "In-Service", "Farm"};
    
    /**
     * Array defining the valid gender options for all animals
     */
    private static String[] eligibleGenderList = {"Male", "Female"};
    
    /**
     * Array defining the primary animal types managed by the system
     * Used for initial selection during intake and reservation
     */
    private static String[] eligibleAnimalsList = {"Dog", "Monkey"};
    
    // -------------------------------------------------------------------------------- //
 	// ---------------------------- 3. DISPLAY FORMATTING ----------------------------- //
 	// -------------------------------------------------------------------------------- //
    
    /**
     * Format strings for consistent column widths when printing the Dog list
     * Used with System.out.printf() in printAnimals() and reserveAnimal()
     */
    static final String DOG_FORMAT = "%-15s %-15s %-20s %-20s %-20s%n";
    static final String MONKEY_FORMAT = "%-15s %-15s %-20s %-20s %-20s%n";
    
    // --- Console Color Constants ---
    
    /** Color constant for ERROR messages and disabled options (e.g., "[ERROR]") */
    public static final String RED = "\u001B[31m";
    
    /** Color constant for SUCCESS messages (e.g., "[SUCCESS]") */
    public static final String GREEN = "\u001B[32m";
    
    /** Color constant for WARNING messages (e.g., "[WARNING]") */
    public static final String YELLOW = "\u001B[33m";
    
    /** Color constant for general information or prompts (e.g., "[ENTER]") */
    public static final String CYAN = "\u001B[36m";
    
    /** ANSI code to reset the console color to the default setting */
    public static final String RESET = "\u001B[0m";
    
    // -------------------------------------------------------------------------------- //
 	// ----------------------------- 4. USER INTERACTION ------------------------------ //
 	// -------------------------------------------------------------------------------- //
    
    /** Scanner object used globally for all user input throughout the program */
    private static Scanner scanner = new Scanner(System.in);
    
    /** Holds the raw String input captured from the user in the main menu loop */
    private static String userInput;
    
    /** Holds the parsed integer choice from the user in the main menu loop */
    private static int choice;

    public static void main(String[] args) {
    	
    	// Initialize lists with sample data for testing
        initializeDogList();
        initializeMonkeyList();
        
        // Infinite menu loop, program continues until user quits
        while (true) {
        	
        	displayMenu(); // Show main menu options
        	System.out.print("\n> ");
        	userInput = scanner.nextLine(); // Capture user choice
        	
        	if (userInput.equalsIgnoreCase("q")) { // Input is "q"
        		System.out.println();
        		System.out.println(RED + "[PROGRAM TERMINATED]" + RESET);
        		return;
        	}
        	else if (userInput.isEmpty()) { // Empty input
        		System.out.println();
        		System.out.println(RED + "[ERROR]" + RESET + " No input provided");
        		System.out.println();
        	}
        	else if (userInput.matches("\\d+")) { // Input must be a number
        		choice = Integer.parseInt(userInput);
        		
        		// Handle user input
            	switch (choice) {
            	
            	case 1: // Intake a new dog
            		
            		intakeNewDog(scanner);
            		break;
            		
            	case 2: // Intake a new monkey
            		
            		intakeNewMonkey(scanner);
            		break;
            		
            	case 3: // Reserve an animal
            		
            		reserveAnimal(scanner);
            		break;
            		
            	case 4: // Print all dogs
            		
            		printAnimals("dog");
            		pause();
            		break;
            		
            	case 5: // Print all monkeys
            		
            		printAnimals("monkey");
            		pause();
            		break;	
            		
            	case 6: // Print all available animals (in service and not reserved)
            		
            		printAnimals("available");
            		pause();
            		break;
            		
            	default: // Out of range
            		
            		System.out.println();
            		System.out.println(RED + "[ERROR]" + RESET + " Out of range: Enter an integer from 1-6");
            		System.out.println();

            	}
        	}
        	else { // Invalid input (input is not an integer)
        		System.out.println();
        		System.out.println(RED + "[ERROR]" + RESET + " Invalid input: Enter a positive integer");
        		System.out.println();
        		
        	}
        	
        }

    }

    // Prints the menu
    public static void displayMenu() {
    	
        System.out.println("\t\t\t\tRescue Animal System Menu");
        System.out.println("[1] Intake a new dog");
        System.out.println("[2] Intake a new monkey");
        System.out.println("[3] Reserve an animal");
        System.out.println("[4] Print a list of all dogs");
        System.out.println("[5] Print a list of all monkeys");
        System.out.println("[6] Print a list of all animals that are not reserved");
        System.out.println("[q] Quit application");
        System.out.println();
        System.out.print("Enter a menu selection: ");
        
    }


    /**
     * Initializes the dogList with sample Dog objects for testing purposes
     * This provides initial data for reservation and reporting features
     */
    public static void initializeDogList() {
    	
    	// Each dog object has attributes such as, name, breed, gender, age, weight, acquisition info, training status, reserved status, service country
    	Dog dog1 = new Dog("Spot", "German Shepherd", "Male", 1, 25.6, "05-12-2019",
    	        "United States", "Intake", false, "United States");
    	Dog dog2 = new Dog("Rex", "Great Dane", "Male", 3, 35.2, "02-03-2020",
    	        "United States", "Phase I", false, "United States");
    	Dog dog3 = new Dog("Bella", "Chihuahua", "Female", 4, 25.6, "12-12-2019", 
    	        "Canada", "In-Service", true, "Canada");
    	Dog dog4 = new Dog("Max", "Labrador Retriever", "Male", 2, 30.1, "03-15-2021",
    	        "United States", "Phase II", false, "United States");
    	Dog dog5 = new Dog("Lucy", "Beagle", "Female", 5, 20.3, "07-09-2018",
    	        "Canada", "Phase III", false, "Canada");
    	Dog dog6 = new Dog("Charlie", "Golden Retriever", "Male", 1, 28.7, "11-20-2022",
    	        "United States", "Intake", false, "United States");
    	Dog dog7 = new Dog("Molly", "Poodle", "Female", 4, 18.4, "09-14-2019",
    	        "France", "Phase I", false, "France");
    	Dog dog8 = new Dog("Duke", "Rottweiler", "Male", 3, 40.2, "01-05-2020",
    	        "Germany", "Phase IV", false, "Germany");
    	Dog dog9 = new Dog("Sadie", "Boxer", "Female", 6, 27.9, "04-22-2017",
    	        "United Kingdom", "Farm", false, "United Kingdom");
    	Dog dog10 = new Dog("Bailey", "Border Collie", "Female", 2, 19.6, "06-30-2021",
    	        "Australia", "Phase V", false, "Australia");
    	Dog dog11 = new Dog("Rocky", "Doberman Pinscher", "Male", 3, 33.8, "10-18-2020",
    	        "Brazil", "Phase II", false, "Brazil");
    	Dog dog12 = new Dog("Zoe", "Husky", "Female", 1, 29.4, "12-11-2022",
    	        "Russia", "Intake", false, "Russia");
    	Dog dog13 = new Dog("Oscar", "Dalmatian", "Male", 4, 26.1, "08-27-2019",
    	        "Spain", "In-Service", false, "Spain");
    	Dog dog14 = new Dog("Luna", "Shiba Inu", "Female", 3, 16.8, "02-17-2020",
    	        "Japan", "Phase IV", false, "Japan");
    	Dog dog15 = new Dog("Hunter", "Mastiff", "Male", 5, 50.7, "05-29-2018",
    	        "United States", "Farm", false, "United States");
    	Dog dog16 = new Dog("Ruby", "Corgi", "Female", 2, 14.2, "03-04-2021",
    	        "United Kingdom", "Phase I", false, "United Kingdom");
    	Dog dog17 = new Dog("Cooper", "German Shorthaired Pointer", "Male", 4, 31.3, "09-08-2019",
    	        "United States", "Phase V", false, "United States");
    	Dog dog18 = new Dog("Nala", "Australian Shepherd", "Female", 3, 22.9, "01-22-2020",
    	        "Australia", "In-Service", true, "Australia");
    	Dog dog19 = new Dog("Jax", "Belgian Malinois", "Male", 2, 28.4, "10-12-2021",
    	        "Belgium", "Phase II", false, "Belgium");
    	Dog dog20 = new Dog("Penny", "Bichon Frise", "Female", 6, 12.3, "07-03-2017",
    	        "France", "Farm", false, "France");
    	Dog dog21 = new Dog("Finn", "Vizsla", "Male", 3, 24.7, "04-10-2020",
    	        "Hungary", "Phase III", false, "Hungary");
    	Dog dog22 = new Dog("Hazel", "Weimaraner", "Female", 4, 29.6, "06-16-2019",
    	        "Germany", "Phase IV", false, "Germany");
    	Dog dog23 = new Dog("Toby", "Saint Bernard", "Male", 5, 55.2, "02-28-2018",
    	        "Switzerland", "In-Service", true, "Switzerland");
    	Dog dog24 = new Dog("Ellie", "Samoyed", "Female", 2, 21.4, "05-05-2021",
    	        "Russia", "Phase I", false, "Russia");
        
        // Add all dogs to dogList
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        dogList.add(dog4);
        dogList.add(dog5);
        dogList.add(dog6);
        dogList.add(dog7);
        dogList.add(dog8);
        dogList.add(dog9);
        dogList.add(dog10);
        dogList.add(dog11);
        dogList.add(dog12);
        dogList.add(dog13);
        dogList.add(dog14);
        dogList.add(dog15);
        dogList.add(dog16);
        dogList.add(dog17);
        dogList.add(dog18);
        dogList.add(dog19);
        dogList.add(dog20);
        dogList.add(dog21);
        dogList.add(dog22);
        dogList.add(dog23);
        dogList.add(dog24);
        
    }


    /**
     * Initializes the monkeyList with sample Monkey objects for testing purposes
     * This provides initial data for reservation and reporting features
     */
    public static void initializeMonkeyList() {
    	
    	Monkey monkey1 = new Monkey("Kiko", "Male", 7, 22.3, "04-11-2021", "Brazil", "Phase II",
    	        false, "United States", 24.5, 55.2, 40.8, "Capuchin");
    	Monkey monkey2 = new Monkey("Mira", "Female", 4, 18.6, "09-03-2022", "Thailand", "Phase III",
    	        true, "Canada", 30.7, 60.3, 45.6, "Macaque");
    	Monkey monkey3 = new Monkey("Zuzu", "Male", 6, 25.0, "01-18-2020", "Kenya", "In-Service",
    	        false, "United Kingdom", 28.1, 58.9, 42.1, "Tamarin");
    	Monkey monkey4 = new Monkey("Loki", "Male", 5, 20.1, "07-22-2021", "Peru", "Phase I",
    	        false, "United States", 26.5, 50.7, 38.2, "Capuchin");
    	Monkey monkey5 = new Monkey("Rina", "Female", 3, 16.4, "02-09-2023", "India", "Intake",
    	        false, "India", 22.6, 48.3, 36.1, "Macaque");
    	Monkey monkey6 = new Monkey("Toto", "Male", 8, 27.8, "11-15-2020", "Congo", "Phase IV",
    	        false, "France", 32.0, 62.4, 46.3, "Tamarin");
    	Monkey monkey7 = new Monkey("Nini", "Female", 6, 24.5, "06-03-2020", "Indonesia", "Phase V",
    	        true, "Australia", 31.3, 59.6, 44.2, "Macaque");
    	Monkey monkey8 = new Monkey("Bobo", "Male", 4, 19.2, "03-19-2022", "Colombia", "In-Service",
    	        false, "Colombia", 25.4, 52.6, 39.9, "Capuchin");
    	Monkey monkey9 = new Monkey("Lala", "Female", 7, 23.7, "12-25-2019", "Vietnam", "Phase II",
    	        false, "United Kingdom", 29.2, 57.1, 43.6, "Tamarin");
    	Monkey monkey10 = new Monkey("Zico", "Male", 5, 21.9, "09-14-2021", "South Africa", "In-Service",
    	        false, "South Africa", 27.1, 54.7, 41.5, "Capuchin");
    	
    	// Add all monkeys to monkeyList
    	monkeyList.add(monkey1);
    	monkeyList.add(monkey2);
    	monkeyList.add(monkey3);
    	monkeyList.add(monkey4);
    	monkeyList.add(monkey5);
    	monkeyList.add(monkey6);
    	monkeyList.add(monkey7);
    	monkeyList.add(monkey8);
    	monkeyList.add(monkey9);
    	monkeyList.add(monkey10);
    	
    }


    /**
     * Guides the user through the process of adding a new Dog to the system
     * It checks for duplicate names and uses helper methods to validate all required fields
     * If intake is successful, the new Dog object is added to the dogList
     *
     * @param scanner The Scanner object used for capturing user input
     */
    public static void intakeNewDog(Scanner scanner) {
    	
    	String name, breed, gender = "", acquisitionDate = "", acquisitionCountry, trainingStatus = "", inServiceCountry;
    	int age = 1;
    	double weight = 1.0;
    	boolean reserved = false;
    	String userChoice;
    	
    	System.out.println();
    	printCenteredTitle(" DOG INTAKE ", 32);
    	System.out.println();
    	System.out.println("To skip a field, press " + CYAN + "[ENTER]" + RESET);
    	
    	// Ask user for dog name
    	name = ask("name", "name", "", "", true, false);
        
        // Check for duplicates
        for(Dog dog: dogList) {
            if(dog.getName().equalsIgnoreCase(name)) {
            	System.out.println();
            	System.out.println(RED + "[ERROR]" + RESET + " This dog already exists in the system: " + name);
            	System.out.println();
                return; //returns to menu
            }
        }
        
        // Ask user for remaining dog info

        breed = ask("breed", "breed", "", "German Shepherd, Golden Retriever, Boxer", true, false);

        gender = askList("gender", eligibleGenderList, gender, null);

        age = askInteger("age", "age", age, 1, 30, "n year(s)", true);

        weight = askDouble("weight", "weight", weight, 1.0, 350.0, "n.n lb(s)", true);

        acquisitionDate = askDate("acquisition date", acquisitionDate);

        acquisitionCountry = ask("acquisition country", "acquisition country", "", "United States, United Kingdom, France, etc.", true, false);

        trainingStatus = askList("training status", eligibleTrainingStatusList, trainingStatus, null);

        reserved = askBoolean("reservation status", reserved, false);
        
        inServiceCountry = ask("in-service country", "in-service country", "", "United States, United Kingdom, France, etc.", true, false);
        
        System.out.println();
        System.out.println(GREEN + "[SUCCESS]" + RESET + " All fields entered successfully");
        System.out.println();
        printDivider("medium");
        System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to proceed to edit mode");
        printDivider("medium");
        scanner.nextLine();
        
        // Edit mode allows user to review and edit important input fields before creation
        boolean exitLoop = false;
        
        while (!exitLoop) {
        	
        	printCenteredTitle(" EDIT MODE ", 32);
            System.out.println("[1] Name: " + (name.isEmpty() ? "None": name));
            System.out.println("[2] Gender: " + (gender.isEmpty() ? "None": gender));
        	System.out.println("[3] Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
        	System.out.println("[4] Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
        	System.out.println("[5] Breed: " + (breed.isEmpty() ? "None": breed));
        	System.out.println("[6] Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
        	System.out.println("[7] Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
        	System.out.println("[8] Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
        	System.out.println("[9] In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
        	System.out.println("[10] Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
        	printDivider("medium");
        	System.out.println("Enter number to edit a field or press " + CYAN + "[ENTER]" + RESET + " to finish:");
        	
        	System.out.print("> ");
        	userChoice = scanner.nextLine().trim();
        	
        	switch (userChoice) {
        	
        	case "1": // Edit name
        		
        		name = ask("new name", "name", name, "", true, false);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Name updated successfully");
        		System.out.println();
        		break;
        		
        	case "2": // Edit gender
        		
        		gender = askList("gender", eligibleGenderList, gender, null);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Gender updated successfully");
        		System.out.println();
        		break;
        		
        	case "3": // Edit age
        		
        		age = askInteger("new age", "age", age, 1, 30, "n", true);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Age updated successfully");
        		System.out.println();
        		break;
        		
        	case "4": // Edit weight
        		
        		weight = askDouble("new weight", "weight", weight, 1.0, 350.0, "n.n", true);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Weight updated successfully");
        		System.out.println();
        		break;
        		
        	case "5": // Edit breed
        		
        		breed = ask("new breed", "breed", breed, "German Shepherd, Golden Retriever, Boxer", true, false);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Breed updated successfully");
        		System.out.println();
        		break;
        		
        	case "6": // Edit training status
        		
        		trainingStatus = askList("training status", eligibleTrainingStatusList, trainingStatus, null);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Training status updated successfully");
        		System.out.println();
        		break;
        		
        	case "7": // Edit acquisition country
        		
        		acquisitionCountry = ask("new acquisition country", "acquisition country", acquisitionCountry, "United States, United Kingdom, France", true, false);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Acquisition country updated successfully");
        		System.out.println();
        		break;
        		
        	case "8": // Edit reservation status
        		
        		reserved = askBoolean("reservation status", reserved, true);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Reservation status updated successfully");
        		System.out.println();
        		break;
        		
        	case "9": // Edit in-service country
        		
        		inServiceCountry = ask("new in-service country", "in-service country", inServiceCountry, "United States, United Kingdom, France", true, false);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " In-service country updated successfully");
        		System.out.println();
        		break;
        		
        	case "10": // Edit acquisition date
        		
        		acquisitionDate = askDate("acquisition date", acquisitionDate);
        		System.out.println();
        		System.out.println(GREEN + "[SUCCESS]" + RESET + " Acquisition date updated successfully");
        		System.out.println();
        		break;
        		
        	case "": // Exit edit mode
        		
        		System.out.println();
        		exitLoop = true;
        		break;
        		
        	default: // Invalid input
        		
        		System.out.println();
        		System.out.println(RED + "[ERROR]" + RESET + " Invalid input");
        		System.out.println();
        		
        	}
        	
        }
        
        printCenteredTitle(" FINAL REVIEW ", 32);
        System.out.println("Name: " + (name.isEmpty() ? "None": name));
        System.out.println("Gender: " + (gender.isEmpty() ? "None": gender));
        System.out.println("Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
    	System.out.println("Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
    	System.out.println("Breed: " + (breed.isEmpty() ? "None": breed));
    	System.out.println("Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
    	System.out.println("Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
    	System.out.println("Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
    	System.out.println("In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
    	System.out.println("Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
    	printDivider("medium");
    	System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to confirm intake");
    	printDivider("medium");
    	scanner.nextLine();
        
        // Create dog object and add to list
        Dog dog = new Dog(name, breed, gender, age, weight, acquisitionDate, acquisitionCountry,
        		trainingStatus, reserved, inServiceCountry);
        dogList.add(dog);
        
        System.out.println(GREEN + "[SUCCESS]" + RESET + " Dog added successfully");
        System.out.println();
        
    }

        /**
         * Guides the user through the process of adding a new Monkey to the system
         * It checks for duplicate names, validates the monkey species against eligibleSpeciesList,
         * and uses helper methods to validate all other required fields (including specific physical measurements)
         * If intake is successful, the new Monkey object is added to the monkeyList
         *
         * @param scanner The Scanner object used for capturing user input.
         */
        public static void intakeNewMonkey(Scanner scanner) {
        	
        	String species = "", name, gender = "", acquisitionDate = "", acquisitionCountry, trainingStatus = "", 
        	inServiceCountry;
        	int age = 1;
        	double weight = 1.0, tailLength = 1.0, height = 1.0, bodyLength = 1.0;
        	boolean reserved = false;
        	String userChoice;
        	
        	System.out.println();
        	printCenteredTitle(" MONKEY INTAKE ", 32);
        	System.out.println();
        	System.out.println("To skip a field, press " + CYAN + "[ENTER]" + RESET);
        	
        	name = ask("name", "name", "", "", true, false);
            
            // Check for duplicates
            for (Monkey monkey: monkeyList) {
            	if(monkey.getName().equalsIgnoreCase(name)) {
            		System.out.println();
            		System.out.println(RED + "[ERROR]" + RESET + " This monkey already exists in the system: " + name);
            		System.out.println();
            		return;
            	}
            }
            
            // Check if species is eligible (infinite loop until user enters valid species)
            while (true) {
            	
                species = askList("eligible monkey species", eligibleSpeciesList, species, null);
                break;
                
            }
            
            // Gather additional information about monkey
            gender = askList("gender", eligibleGenderList, gender, null);
            
            age = askInteger("age", "age", age, 1, 100, "n year(s)", true);
            
            weight = askDouble("weight", "weight", weight, 1.0, 100.0, "n.n lb(s)", true);
            
            acquisitionDate = askDate("acquisition date", acquisitionDate);
            
            acquisitionCountry = ask("acquisition country", "acquisition country", "", "United States, United Kingdom, France", true, false);
            
            trainingStatus = askList("training status", eligibleTrainingStatusList, trainingStatus, null);
            
            reserved = askBoolean("reservation status", reserved, false);
            
            inServiceCountry = ask("in-service country", "in-service country", "", "United States, United Kingdom, France", true, false);
            
            tailLength = askDouble("tail length", "tail length", tailLength, 1.0, 50.0, "n.n inch(es)", true);
            
            height = askDouble("height", "height", height, 1.0, 100.0, "n.n inch(es)", true);
            
            bodyLength = askDouble("body length", "body length", bodyLength, 1.0, 100.0, "n.n inch(es)", true);
            
            System.out.println();
            System.out.println(GREEN + "[SUCCESS]" + RESET + " All fields entered successfully");
            System.out.println();
            printDivider("medium");
            System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to proceed to edit mode");
            printDivider("medium");
            scanner.nextLine();
            
            boolean exitLoop = false;
            
            while (!exitLoop) {
            	
            	printCenteredTitle(" EDIT MODE ", 32);
                System.out.println("[1] Name: " + (name.isEmpty() ? "None": name));
                System.out.println("[2] Gender: " + (gender.isEmpty() ? "None": gender));
                System.out.println("[3] Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
            	System.out.println("[4] Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
            	System.out.println("[5] Species: " + (species.isEmpty() ? "None": species));
            	System.out.println("[6] Tail Length: " + (tailLength <= 0.0 ? "None": tailLength == 1 ? tailLength + " inch": tailLength + " inches"));
            	System.out.println("[7] Height: " + (height <= 0.0 ? "None": height == 1 ? height + " inch": height + " inches"));
            	System.out.println("[8] Body Length: " + (bodyLength <= 0.0 ? "None": bodyLength == 1 ? bodyLength + " inch": bodyLength + " inches"));
            	System.out.println("[9] Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
            	System.out.println("[10] Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
            	System.out.println("[11] Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
            	System.out.println("[12] In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
            	System.out.println("[13] Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
            	printDivider("medium");
            	System.out.println("Enter number to edit a field or press " + CYAN + "[ENTER]" + RESET + " to finish:");
            	
            	System.out.print("> ");
            	userChoice = scanner.nextLine().trim();
            	
            	switch (userChoice) {
            	
            	case "1":
            		
            		name = ask("new name", "name", name, "", true, false);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Name updated successfully");
            		System.out.println();
            		break;
            		
            	case "2":
            		
            		gender = askList("gender", eligibleGenderList, gender, null);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Gender updated successfully");
            		System.out.println();
            		break;
            		
            	case "3":
            		
            		age = askInteger("new age", "age", age, 1, 100, "n", true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Age updated successfully");
            		System.out.println();
            		break;
            		
            	case "4":
            		
            		weight = askDouble("new weight", "weight", weight, 1.0, 100.0, "n.n", true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Weight updated successfully");
            		System.out.println();
            		break;
            		
            	case "5":
            		
            		species = askList("species", eligibleSpeciesList, species, null);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Species updated successfully");
            		System.out.println();
            		break;
            		
            	case "6":
            		
            		tailLength = askDouble("new tail length", "tail length", tailLength, 1.0, 50.0, "n", true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Tail length updated successfully");
            		System.out.println();
            		break;
            		
            	case "7":
            		
            		height = askDouble("new height", "height", height, 1.0, 50.0, "n", true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Height updated successfully");
            		System.out.println();
            		break;
            		
            	case "8":
            		
            		bodyLength = askDouble("new body length", "body length", bodyLength, 1.0, 50.0, "n", true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Body length updated successfully");
            		System.out.println();
            		break;
            		
            	case "9":
            		
            		trainingStatus = askList("training status", eligibleTrainingStatusList, trainingStatus, null);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Training status updated successfully");
            		System.out.println();
            		break;
            		
            	case "10":
            		
            		acquisitionCountry = ask("new acquisition country", "acquisition country", acquisitionCountry, "United States, United Kingdom, France", true, false);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Acquisition country updated successfully");
            		System.out.println();
            		break;
            		
            	case "11":
            		
            		reserved = askBoolean("reservation status", reserved, true);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Reservation status updated successfully");
            		System.out.println();
            		break;
            		
            	case "12":
            		
            		inServiceCountry = ask("new in-service country", "in-service country", inServiceCountry, "United States, United Kingdom, France", true, false);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " In-service country updated successfully");
            		System.out.println();
            		break;
            		
            	case "13":
            		
            		acquisitionDate = askDate("acquisition date", acquisitionDate);
            		System.out.println();
            		System.out.println(GREEN + "[SUCCESS]" + RESET + " Acquisition date updated successfully");
            		System.out.println();
            		break;
            		
            	case "":
            		
            		System.out.println();
            		exitLoop = true;
            		break;
            		
            	default:
            		
            		System.out.println();
            		System.out.println(RED + "[ERROR]" + RESET + " Invalid input");
            		System.out.println();
            		
            	}
            	
            }
            
            printCenteredTitle(" FINAL REVIEW ", 32);
            System.out.println("Name: " + (name.isEmpty() ? "None": name));
            System.out.println("Gender: " + (gender.isEmpty() ? "None": gender));
            System.out.println("Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
        	System.out.println("Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
        	System.out.println("Species: " + (species.isEmpty() ? "None": species));
        	System.out.println("Tail Length: " + (tailLength <= 0.0 ? "None": tailLength == 1 ? tailLength + " inch": tailLength + " inches"));
        	System.out.println("Height: " + (height <= 0.0 ? "None": height == 1 ? height + " inch": height + " inches"));
        	System.out.println("Body Length: " + (bodyLength <= 0.0 ? "None": bodyLength == 1 ? bodyLength + " inch": bodyLength + " inches"));
        	System.out.println("Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
        	System.out.println("Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
        	System.out.println("Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
        	System.out.println("In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
        	System.out.println("Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
        	printDivider("medium");
        	System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to confirm intake");
        	printDivider("medium");
        	scanner.nextLine();
            
            Monkey monkey = new Monkey(name, gender, age, weight, acquisitionDate, acquisitionCountry, trainingStatus,
            		reserved, inServiceCountry, tailLength, height, bodyLength, species);
            monkeyList.add(monkey);
            
            System.out.println(GREEN + "[SUCCESS]" + RESET + " Monkey added successfully");
            System.out.println();
            
        }

        /**
         * Reserves an available animal (Dog or Monkey) based on country and animal type
         * The method prompts the user to select a country and animal type, displays a list of unreserved animals,
         * allows the user to select an animal by name, and updates its reserved status to true upon confirmation
         * * @param scanner The Scanner object used for capturing user input
         */
        public static void reserveAnimal(Scanner scanner) {
        	
        	String userName, country, animalType = "", name, gender, acquisitionDate, acquisitionCountry, trainingStatus, inServiceCountry, species, breed;
        	int age;
        	double weight, tailLength, height, bodyLength;
        	boolean foundAny = false, foundDogs = false, foundMonkeys = false, reserved;
        	boolean[] disabledOptions = {}; 
        	
        	System.out.println();
        	printCenteredTitle(" ANIMAL RESERVATION ", 32);
        	
        	// Ask user for country and animal type
        	country = ask("search country", "country", "", "United States, United Kingdom, France", true, false);
        	
        	if (country.isEmpty()) { // If no country is provided, default to "United States"
        		System.out.println();
        		System.out.println(YELLOW + "[WARNING]" + RESET + " No country provided: Defaulting to \"United States\"");
        		country = "United States";
        	}
        	
        	for (Dog dog: dogList) { // Check if dogs exist in country
        		inServiceCountry = dog.getInServiceCountry();
        		reserved = dog.getReserved();
        		if ((inServiceCountry.equalsIgnoreCase(country)) && (!reserved)) {
        			foundDogs = true;
        			break;
        		}
        	}
        	
        	for (Monkey monkey: monkeyList) { // Check if monkeys exist in country
        		inServiceCountry = monkey.getInServiceCountry();
        		reserved = monkey.getReserved();
        		if ((inServiceCountry.equalsIgnoreCase(country)) && (!reserved)) {
        			foundMonkeys = true;
        			break;
        		}
        	}
        	
        	if (!foundDogs && !foundMonkeys) { // If no dogs or monkeys were found in country, exit method
        		System.out.println();
        		System.out.println(RED + "[NO RESULTS] " + RESET + "No unreserved animals in country: " + country);
        		System.out.println();
        		return;
        	}
        	else if (!foundDogs) {
        		System.out.println();
        		System.out.println(YELLOW + "[WARNING] " + RESET + "No unreserved dogs in country: " + country);
        	}
        	else if (!foundMonkeys) {
        		System.out.println();
        		System.out.println(YELLOW + "[WARNING] " + RESET + "No unreserved monkeys in country: " + country);
        	}
        	
        	disabledOptions = new boolean[] {!foundDogs, !foundMonkeys}; // Disabled options based on if dogs or monkeys were found
            
        	animalType = askList("animal type", eligibleAnimalsList, animalType, disabledOptions);
        	
        	if (animalType.isEmpty()) {
        		System.out.println();
        		if (foundDogs) {
        			System.out.println(YELLOW + "[WARNING]" + RESET + " No animal type provided: Defaulting to \"Dog\"");
        			animalType = "Dog";
        		}
        		else if (foundMonkeys) {
        			System.out.println(YELLOW + "[WARNING]" + RESET + " No animal type provided: Defaulting to \"Monkey\"");
        			animalType = "Monkey";
        		}
        	}
        	
        	foundAny = false;
            
            // Display list of available animals in that country
            switch (animalType) {
            
            case "Dog": // Dog animal type is provided
            	System.out.println();
            	printCenteredTitle(" DOGS ", 90);
            	System.out.println();
            	System.out.printf(DOG_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country"); // Print in the dog format with these columns
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	// Loop through dog list and print out each dog and its details
            	for (Dog dog: dogList) {
            		inServiceCountry = dog.getInServiceCountry();
            		reserved = dog.getReserved();
            		if ((inServiceCountry.equalsIgnoreCase(country)) && (!reserved)) {
            			foundAny = true;
            			name = dog.getName();
            			trainingStatus = dog.getTrainingStatus();
            			acquisitionCountry = dog.getAcquisitionLocation();
            			
            			System.out.printf(DOG_FORMAT,
                				(name.isEmpty() ? "None": name),
                				(trainingStatus.isEmpty() ? "None": trainingStatus),
                				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
                				(reserved ? "Reserved": "Not reserved"),
                				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
                		);
                		
                		System.out.println();
            		}
            	}
            	
            	// No results were found
            	if (!foundAny) {
            		System.out.println();
            		System.out.println(RED + "[NO RESULTS]" + RESET + " There are no unreserved dogs in country: " + country);
            		System.out.println();
            		return;
            	}
            	
            	// Let user choose an animal by name to reserve
            	userName = ask("name of dog to reserve", "name", "", "", false, false);
            	
            	// Loop through dog list to find user provided name
            	for (Dog dog: dogList) {
            		name = dog.getName();
            		inServiceCountry = dog.getInServiceCountry();
            		reserved = dog.getReserved();
            		if ((name.equalsIgnoreCase(userName)) && (inServiceCountry.equalsIgnoreCase(country)) && (!reserved)) {
            			gender = dog.getGender();
            			age = dog.getAge();
            			weight = dog.getWeight();
            			breed = dog.getBreed();
            			trainingStatus = dog.getTrainingStatus();
            			acquisitionCountry = dog.getAcquisitionLocation();
            			acquisitionDate = dog.getAcquisitionDate();
            			
            			System.out.println();
            			printCenteredTitle(" FINAL REVIEW ", 32);
            	        System.out.println("Name: " + (name.isEmpty() ? "None": name));
            	        System.out.println("Gender: " + (gender.isEmpty() ? "None": gender));
            	        System.out.println("Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
            	    	System.out.println("Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
            	    	System.out.println("Breed: " + (breed.isEmpty() ? "None": breed));
            	    	System.out.println("Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
            	    	System.out.println("Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
            	    	System.out.println("Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
            	    	System.out.println("In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
            	    	System.out.println("Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
            	    	printDivider("medium");
            	    	System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to confirm reservation");
            	    	printDivider("medium");
            	    	scanner.nextLine();
            	    	dog.setReserved(true); // Update reserved status to true for found dog
            	    	System.out.println(GREEN + "[SUCCESS]" + RESET + " Dog reserved successfully");
            	        System.out.println();
            			return; // Exit method
            		}
            	}
            	
            	System.out.println();
            	System.out.println(RED + "[ERROR]" + RESET + " Failed to find dog under name: " + userName); // If code runs to this point, no dog under user provided name was found
            	System.out.println();
            	return;
            	
            case "Monkey": // Monkey animal type is provided
            	
            	System.out.println();
            	printCenteredTitle(" MONKEYS ", 90);
            	System.out.println();
            	System.out.printf(MONKEY_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country");
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	// Loop through monkey list and print out each monkey and its details
            	for (Monkey monkey: monkeyList) {
            		inServiceCountry = monkey.getInServiceCountry();
            		reserved = monkey.getReserved();
            		if ((inServiceCountry.equalsIgnoreCase(country)) && (!reserved)) {
            			foundAny = true;
            			name = monkey.getName();
            			trainingStatus = monkey.getTrainingStatus();
            			acquisitionCountry = monkey.getAcquisitionLocation();
            			
            			System.out.printf(MONKEY_FORMAT,
                				(name.isEmpty() ? "None": name),
                				(trainingStatus.isEmpty() ? "None": trainingStatus),
                				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
                				(reserved ? "Yes": "No"),
                				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
                		);
                		
                		System.out.println();
            		}
            	}
            	
            	if (!foundAny) {
            		System.out.println();
            		System.out.println(RED + "[NO RESULTS]" + RESET + " There are no unreserved monkeys in country: " + country);
            		System.out.println();
            		return;
            	}
            	
            	userName = ask("monkey name", "name", "", "", true, false);
            	
            	// Loop through monkey list to find user provided name
            	for (Monkey monkey: monkeyList) {
            		name = monkey.getName();
            		inServiceCountry = monkey.getInServiceCountry();
            		reserved = monkey.getReserved();
            		if ((name.equalsIgnoreCase(userName)) && (inServiceCountry.equalsIgnoreCase(country)) && ((!reserved))) {
            			gender = monkey.getGender();
            			age = monkey.getAge();
            			weight = monkey.getWeight();
            			species = monkey.getSpecies();
            			tailLength = monkey.getTailLength();
            			height = monkey.getHeight();
            			bodyLength = monkey.getBodyLength();
            			trainingStatus = monkey.getTrainingStatus();
            			acquisitionCountry = monkey.getAcquisitionLocation();
            			acquisitionDate = monkey.getAcquisitionDate();
            			
            			System.out.println();
            			printCenteredTitle(" FINAL REVIEW ", 32);
            	        System.out.println("Name: " + (name.isEmpty() ? "None": name));
            	        System.out.println("Gender: " + (gender.isEmpty() ? "None": gender));
            	    	System.out.println("Age: " + (age <= 0 ? "None": age == 1 ? age + " year old": age + " years old"));
            	    	System.out.println("Weight: " + (weight <= 0.0 ? "None": weight == 1 ? weight + " lb": weight + " lbs"));
            	    	System.out.println("Species: " + (species.isEmpty() ? "None": species));
            	    	System.out.println("Tail Length: " + (tailLength <= 0.0 ? "None": tailLength == 1 ? tailLength + " inch": tailLength + " inches"));
                    	System.out.println("Height: " + (height <= 0.0 ? "None": height == 1 ? height + " inch": height + " inches"));
                    	System.out.println("Body Length: " + (bodyLength <= 0.0 ? "None": bodyLength == 1 ? bodyLength + " inch": bodyLength + " inches"));
            	    	System.out.println("Training Status: " + (trainingStatus.isEmpty() ? "None": trainingStatus));
            	    	System.out.println("Acquisition Country: " + (acquisitionCountry.isEmpty() ? "None": acquisitionCountry));
            	    	System.out.println("Reservation Status: " + (reserved ? "Reserved": "Not reserved"));
            	    	System.out.println("In-Service Country: " + (inServiceCountry.isEmpty() ? "None": inServiceCountry));
            	    	System.out.println("Acquisition Date: " + (acquisitionDate.isEmpty() ? "None": acquisitionDate));
            	    	printDivider("medium");
            	    	System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to confirm reservation");
            	    	printDivider("medium");
            	    	scanner.nextLine();
            	    	monkey.setReserved(true);
            	    	System.out.println(GREEN + "[SUCCESS]" + RESET + " Monkey reserved successfully");
            	        System.out.println();
            			return; // Exit method
            		}
            	}
            	
            	System.out.println();
            	System.out.println(RED + "[ERROR]" + RESET + " Failed to find monkey under name: " + userName);
            	System.out.println();
            	return;
            	
            case "": // No animal type was provided
            	
            	System.out.println();
            	System.out.println(RED + "[ERROR]" + RESET + " No animal type provided");
            	System.out.println();
            	return;
            	
            default: // Could not find animal type
            	
            	System.out.println();
            	System.out.println(RED + "[ERROR]" + RESET + " Unrecognized animal type: " + animalType);
            	System.out.println();
            	
            }

        }
        
        /**
         * Capitalizes the first letter of every word in an input string
         *
         * @param input The string to be capitalized
         * @return The string with the first letter of each word capitalized
         */
        public static String capitalize(String input) {
        	
        	String capitalizedString = "";
        	String[] splitWords = input.split(" ");
        	
        	for (String word: splitWords) {
        		if (word.length() > 0) {
        			capitalizedString += Character.toUpperCase(word.charAt(0)) + word.substring(1);
        		}
        		capitalizedString += " ";
        	}
        	
        	return capitalizedString.trim();
        }
        
        /**
         * Prints a divider line to visually separate content in the console
         * The length of the divider is determined by the size parameter ("small", "medium", or "large")
         * * @param size The size of the divider ("small", "medium", or "large")
         */
        public static void printDivider(String size) {
        	
        	switch (size) {
        	
        	case "small": // Small divider
        		
        		System.out.println("-".repeat(16));
        		break;
        		
        	case "medium": // Medium divider
        		
        		System.out.println("-".repeat(32));
        		break;
        		
        	case "large": // Large divider
        		
        		System.out.println("-".repeat(90));
        		break;
        		
        	default: // Invalid size or none provided
        		
        		System.out.println(RED + "[ERROR]" + RESET + " Invalid divider size");
        		
        	}
        }
        
        /**
         * Prints a title string centered within a line of dashes, based on a specified total width
         *
         * @param title The string to be centered
         * @param width The total width (including dashes) for the centered title
         */
        public static void printCenteredTitle(String title, int width) {
        	
        	if (width < title.length() + 2) {
        		width = title.length() + 2; // Prevents errors if the width is too small
        	}
        	
        	int totalSpaces = width - title.length();
        	int left = totalSpaces / 2;
        	int right = totalSpaces - left;
        	
        	String line = "";
        	
        	for (int i = 0; i < left; ++i) {
        		line += "-";
        	}
        	
        	line += title;
        	
        	for (int i = 0; i < right; ++i) {
        		line += "-";
        	}
        	
        	System.out.println(line);
        	
        }
        
        /**
         * Pauses program execution until the user presses the [ENTER] key
         */
        public static void pause() {
        	
        	printDivider("medium");
        	System.out.println("Press " + CYAN + "[ENTER]" + RESET + " to continue");
        	printDivider("medium");
        	scanner.nextLine();
        	
        }
        
        /**
         * Prompts the user to enter a string field (e.g., name, country)
         * Provides options to show the current value, provide an example, and capitalize the result
         *
         * @param label The descriptive label for the field (used in the prompt)
         * @param enterLabel The label used to display the current value (e.g., "Current name:")
         * @param currentValue The existing value of the field (returned if user input is empty)
         * @param example An optional example or format string to show the user
         * @param capitalize If true, the returned string will have the first letter of each word capitalized
         * @param format If true, the example is labeled as "Format," otherwise "Example"
         * @return The user's input string, or the currentValue if the input was empty and a currentValue existed
         */
        public static String ask(String label, String enterLabel, String currentValue, String example, boolean capitalize, boolean format) {
        	
        	System.out.println();
        	
        	if (!currentValue.isEmpty()) { // Show currentValue if not empty
        		System.out.println("Current " + enterLabel + ": " + currentValue);
        	}
        	
        	String prompt = "Enter " + label;
        	if (!example.isEmpty()) { // Show Example/Format if example string is not empty
        		prompt += format ? " (Format: " + example + ")": " (Example: " + example + ")";
        	}
        	
        	prompt += ":";
        	
        	System.out.println(prompt);
        	System.out.print("> ");
        	
        	String input = scanner.nextLine().trim();
        	
        	if ((input.isEmpty()) && (!currentValue.isEmpty())) { // Only return currentValue if user does not input anything and currentValue is not empty
        		return currentValue;
        	}
        	
        	return capitalize ? capitalize(input): input; // Return first letter of each word capitalized if capitalize is true
        	
        }
        
        /**
         * Prompts the user to choose an item from a numbered list
         * Handles list traversal, validates input against range, and can highlight a current value or disable options
         *
         * @param title The title displayed above the numbered list
         * @param items An array of strings representing the options
         * @param currentValue The current value to be highlighted in the list
         * @param disabled An array of booleans to mark options as disabled (true = disabled)
         * @return The selected string from the list, or the currentValue if input is empty, or an empty string if input is blank and no currentValue exists
         */
        public static String askList(String title, String[] items, String currentValue, boolean[] disabled) {
        	
        	String prefix;
        	String itemText;
        	
        	while (true) {
        		System.out.println();
        		printCenteredTitle(" " + title.toUpperCase() + " ", 32);
        		
        		for (int i = 0; i < items.length; ++i) { // Print out every item and highlight current value in green text color
        			prefix = "[" + (i + 1) + "]";
        			itemText = items[i];
        			
        			if ((disabled != null) && (i < (disabled.length) && (disabled[i]))) {
        				System.out.println(RED + prefix + " " + itemText + " [DISABLED]" + RESET);
        			}
        			else if (items[i].equalsIgnoreCase(currentValue)) {
        				System.out.println(GREEN + prefix + " " + itemText + RESET);
        			}
        			else {
        				System.out.println(prefix + " " + itemText);
        			}
        		}
        		
        		printDivider("medium");
        		System.out.println("Choose by number:");
        		System.out.print("> ");
        		
        		String input = scanner.nextLine().trim();
        		
        		if ((input.isEmpty()) && (!currentValue.isEmpty())) {
        			return currentValue;
        		}
        		
        		if (input.isEmpty()) { // If user does not input anything, return an empty string to skip field
        			return "";
        		}
        		
        		if (!input.matches("\\d+")) { // Input is not all digits
        			System.out.println();
        			System.out.println(RED + "[ERROR]" + RESET + " Invalid input: Enter a valid choice from 1-" + items.length);
        			continue;
        		}
        		
        		int index = Integer.parseInt(input); // Convert user input to an integer
        		
        		if (index < 1 || index > items.length) { // User choice is out of range
        			System.out.println();
        			System.out.println(RED + "[ERROR]" + RESET + " Out of range: Enter a valid choice from 1-" + items.length);
        			continue;
        		}
        		
        		if ((disabled != null) && (index - 1 < disabled.length) && (disabled[index - 1])) {
        			System.out.println();
        			System.out.println(RED + "[ERROR]" + RESET + " Option disabled: Enter a valid choice from 1-" + items.length);
        			continue;
        		}
        		
        		return items[index - 1]; // Use index-1 because arrays start at 0
        		
        	}
        	
        }
        
        /**
         * Prompts the user to enter an integer field
         * Provides strong input validation, ensuring the input is a valid integer within the specified minimum and maximum range
         *
         * @param label The descriptive label for the field
         * @param enterLabel The label used to display the current value
         * @param currentValue The existing value of the field (returned if user input is empty)
         * @param minValue The minimum allowed integer value (inclusive)
         * @param maxValue The maximum allowed integer value (inclusive)
         * @param example An optional example or format string
         * @param format If true, the example is labeled as "Format"
         * @return The user's validated integer input, or the currentValue if input was empty
         */
        public static int askInteger(String label, String enterLabel, int currentValue, int minValue, int maxValue, String example, boolean format) {
        	
        	int number;
        	
        	System.out.println();
        	
        	if (currentValue > minValue) {
        		System.out.println("Current " + enterLabel + ": " + currentValue);
        	}
        	
        	String prompt = "Enter " + label;
        	if (!example.isEmpty()) {
        		prompt += format ? " (Format: " + example + ")": " (Example: " + example + ")";
        	}
        	
        	prompt += ":";
        	
        	while (true) {
        		System.out.println(prompt);
            	System.out.print("> ");
            	
            	String input = scanner.nextLine().trim();
            	
            	if ((input.isEmpty()) && (currentValue >= minValue)) {
            		return currentValue;
            	}
            	
            	try {
            		
            		number = Integer.parseInt(input);
            		
            		if (number < minValue || maxValue < number) {
            			throw new Exception(RED + "[ERROR]" + RESET + " Out of range: Enter an integer from " + minValue + "-" + maxValue);
            		}
            		
            		return number;
            	}
            	catch (NumberFormatException e) {
            		System.out.println();
            		System.out.println(RED + "[ERROR]" + RESET + " Invalid input: Enter an integer from " + minValue + "-" + maxValue);
            		System.out.println();
            	}
            	catch (Exception e) {
            		System.out.println();
            		System.out.println(e.getMessage());
            		System.out.println();
            	}
            	
        	}
        	
        }
        
        /**
         * Prompts the user to enter a double (decimal number) field
         * Provides strong input validation, ensuring the input is a valid double within the specified range,
         * and rounds the input to two decimal places
         *
         * @param label The descriptive label for the field
         * @param enterLabel The label used to display the current value
         * @param currentValue The existing value of the field (returned if user input is empty)
         * @param minValue The minimum allowed double value (inclusive)
         * @param maxValue The maximum allowed double value (inclusive)
         * @param example An optional example or format string
         * @param format If true, the example is labeled as "Format"
         * @return The user's validated double input, or the currentValue if input was empty
         */
        public static double askDouble(String label, String enterLabel, double currentValue, double minValue, double maxValue, String example, boolean format) {
        	
        	double number;
        	
        	System.out.println();
        	
        	if (currentValue > minValue) {
        		System.out.println("Current " + enterLabel + ": " + currentValue);
        	}
        	
        	String prompt = "Enter " + label;
        	if (!example.isEmpty()) {
        		prompt += format ? " (Format: " + example + ")": " (Example: " + example + ")";
        	}
        	
        	prompt += ":";
        	
        	while (true) {
        		System.out.println(prompt);
            	System.out.print("> ");
            	
            	String input = scanner.nextLine().trim();
            	
            	if ((input.isEmpty()) && (currentValue >= minValue)) {
            		return currentValue;
            	}
            	
            	try {
            		
            		number = Double.parseDouble(input); // Parse input for double value
            		number = Math.round(number * 100.0) / 100.0; // Ensure weight is 2 decimals at most
            		
            		if (number < minValue || maxValue < number) { // Number is less than the minimum value or more than the max value
            			throw new Exception(RED + "[ERROR]" + RESET + " Out of range: Enter an integer (with a decimal) from " + minValue + "-" + maxValue);
            		}
            		
            		return number;
            	}
            	catch (NumberFormatException e) { // User input is not a numeric value
            		System.out.println();
            		System.out.println(RED + "[ERROR]" + RESET + " Invalid input: Enter an integer (with a decimal) from " + minValue + "-" + maxValue);
            		System.out.println();
            	}
            	catch (Exception e) { // Any other errors (out of range error)
            		System.out.println();
            		System.out.println(e.getMessage());
            		System.out.println();
            	}
            	
        	}
        }
        
        /**
         * Prompts the user to select a boolean field (Yes/No) via a numbered menu (1 or 2)
         *
         * @param title The title displayed above the Yes/No options
         * @param currentValue The existing boolean value
         * @param showCurrent If true, the current value (Yes or No) will be highlighted
         * @return The user's boolean choice (true for Yes, false for No)
         */
        public static boolean askBoolean(String title, boolean currentValue, boolean showCurrent) {
        	
        	// Loop until user enters correct input
        	while (true) {
        		System.out.println();
        		printCenteredTitle(" " + title.toUpperCase() + " ", 32);
        		
        		// Shows current value by highlighting option in color
        		if (showCurrent && currentValue) {
        			System.out.println(GREEN + "[1] Yes" + RESET);
        			System.out.println("[2] No");
        		}
        		else if (showCurrent) {
        			System.out.println("[1] Yes");
        			System.out.println(GREEN + "[2] No" + RESET);
        		}
        		else {
        			System.out.println("[1] Yes");
        			System.out.println("[2] No");
        		}
        		
        		printDivider("medium");
        		System.out.println("Choose by number:");
        		System.out.print("> ");
        		
        		String input = scanner.nextLine().trim();
        		
        		if (input.isEmpty()) {
        			return currentValue;
        		}
        		
        		if (!input.matches("\\d+")) {
        			System.out.println();
        			System.out.println(RED + "[ERROR]" + RESET + " Invalid input: Enter 1 or 2");
        			continue;
        		}
        		
        		int choice = Integer.parseInt(input);
        		
        		if (choice == 1) {
        			return true;
        		}
        		
        		if (choice == 2) {
        			return false;
        		}
        		
        		System.out.println();
        		System.out.println(RED + "[ERROR]" + RESET + " Out of range: Enter 1 or 2");
        	}
        }
        
        /**
         * Prompts the user to enter a date string in MM-DD-YYYY format
         * Validation is performed using the isValidDate() method
         *
         * @param label The descriptive label for the field (e.g., "Acquisition Date")
         * @param currentValue The existing date string (returned if user input is empty)
         * @return The user's validated date string, or the currentValue if input was empty
         */
        public static String askDate(String label, String currentValue) {
        	
        	System.out.println();
        	
        	if (!currentValue.isEmpty()) {
        		System.out.println("Current " + label + ": " + currentValue);
        	}
        	
        	System.out.println("Enter " + label + " (Format: MM-DD-YYYY)");
        	System.out.print("> ");
        	
        	String input = scanner.nextLine().trim();
        		
        	if ((input.isEmpty()) && (!currentValue.isEmpty())) {
        		return currentValue;
        	}
        	else if (input.isEmpty()) {
        		return "";
        	}
        		
        	while (!isValidDate(input).isEmpty()) { // Valid date should not return an error, if no error return value is empty
        		System.out.println();
        		System.out.println(isValidDate(input));
        		System.out.println();
        		System.out.println("Enter " + label + " (Format: MM-DD-YYYY)");
        		System.out.print("> ");
        		input = scanner.nextLine().trim();
        		
        		if (input.isEmpty()) { // Return empty string if user input is blank
        			return "";
        		}
        		
        	}
        	
        	return input;
        	
        }
        
        /**
         * Validates a date string to ensure it is in the correct MM-DD-YYYY format and represents a valid date
         * Checks for length, hyphen position, digit presence, and logical ranges for month, day, and year
         *
         * @param date The date string to validate
         * @return An empty string if the date is valid, or an error message string if validation fails
         */
        public static String isValidDate(String date) {
        	
        	if (date == null || date.length() != 10) { // Date is not initialized or length is not 10 characters
        		return RED + "[ERROR]" + RESET + " Invalid date format: Must be MM-DD-YYYY (10 characters)";
        	}
        	
        	if (date.charAt(2) != '-' || date.charAt(5) != '-') { // The character at index 2 and 5 should be "-" MM-DD-YYYY
        		return RED + "[ERROR]" + RESET + " Invalid date format: Must include hyphens at positions 3 and 6";
        	}
        	
        	String mm = date.substring(0, 2); // Month is between index 0 and 2
        	String dd = date.substring(3, 5);
        	String yyyy = date.substring(6, 10);
        	
        	if (!mm.matches("\\d{2}") || !dd.matches("\\d{2}") || !yyyy.matches("\\d{4}")) { // Month and day are 2 digits, year is 4 digits
        		return RED + "[ERROR]" + RESET + " Invalid date format: Must only contain digits in MM-DD-YYYY";
        	}
        	
        	int month = Integer.parseInt(mm); // Convert from string to integer
        	int day = Integer.parseInt(dd);
        	int year = Integer.parseInt(yyyy);
        	
        	if (month < 1 || month > 12) { // Month needs to be between 1 and 12
        		return RED + "[ERROR]" + RESET + " Invalid date format: Month must be between 01 and 12";
        	}
        	
        	if (day < 1 || day > 31) { // Day needs to be between 1 and 31
        		return RED + "[ERROR]" + RESET + " Invalid date format: Day must be between 01 and 31";
        	}
        	
        	if (year < 0) { // Year needs to be greater than 0
        		return RED + "[ERROR]" + RESET + " Invalid date format: Year must be greater than 0";
        	}
        	
        	return ""; // Did not return for any errors above, return an empty string
        	
        }

        /**
         * Prints lists of animals based on the requested list type
         * * @param listType A string indicating which list to print: "dog", "monkey", or "available" (in-service and not reserved)
         */
        public static void printAnimals(String listType) {
        	
        	String name, acquisitionCountry, trainingStatus, inServiceCountry;
        	boolean reserved;
        	int dogCount = 0;
        	int monkeyCount = 0;
            
            switch (listType) {
            
            case "dog": // Dog list
            	
            	System.out.println();
            	printCenteredTitle(" DOG LIST ", 90);
            	System.out.println();
            	System.out.printf(DOG_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country"); // Print in dog format with these column names
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	for (Dog dog: dogList) {
            		name = dog.getName();
            		trainingStatus = dog.getTrainingStatus();
            		acquisitionCountry = dog.getAcquisitionLocation();
            		reserved = dog.getReserved();
            		inServiceCountry = dog.getInServiceCountry();
            		
            		System.out.printf(DOG_FORMAT,
            				(name.isEmpty() ? "None": name),
            				(trainingStatus.isEmpty() ? "None": trainingStatus),
            				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
            				(reserved ? "Reserved": "Not reserved"),
            				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
            		);
            		
            		System.out.println();
            		
            		dogCount++;
            	}
            	
            	System.out.println();
            	System.out.println("Total dogs: " + dogCount);
            	System.out.println();
            	break;
            
            case "monkey": // Monkey list
            	
            	System.out.println();
            	printCenteredTitle(" MONKEY LIST ", 90);
            	System.out.println();
            	System.out.printf(MONKEY_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country"); // Print in monkey format with these column names
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	for (Monkey monkey: monkeyList) {
            		name = monkey.getName();
            		trainingStatus = monkey.getTrainingStatus();
            		acquisitionCountry = monkey.getAcquisitionLocation();
            		reserved = monkey.getReserved();
            		inServiceCountry = monkey.getInServiceCountry();
            		
            		System.out.printf(MONKEY_FORMAT,
            				(name.isEmpty() ? "None": name),
            				(trainingStatus.isEmpty() ? "None": trainingStatus),
            				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
            				(reserved ? "Reserved": "Not reserved"),
            				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
            		);
            		
            		System.out.println();
            		
            		monkeyCount++;
            	}
            	
            	System.out.println();
            	System.out.println("Total monkeys: " + monkeyCount);
            	System.out.println();
            	break;
            	
            case "available": // Available list
            	
            	System.out.println();
            	printCenteredTitle(" AVAILABLE DOGS ", 90);
            	System.out.println();
            	System.out.printf(DOG_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country"); // Print in dog format with these column names
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	for (Dog dog: dogList) {
            		trainingStatus = dog.getTrainingStatus();
            		reserved = dog.getReserved();
            		if ((trainingStatus.equalsIgnoreCase("In-Service")) && (!reserved)) {
            			name = dog.getName();
                		acquisitionCountry = dog.getAcquisitionLocation();
                		inServiceCountry = dog.getInServiceCountry();
                		
            			System.out.printf(DOG_FORMAT,
                				(name.isEmpty() ? "None": name),
                				(trainingStatus.isEmpty() ? "None": trainingStatus),
                				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
                				(reserved ? "Reserved": "Not reserved"),
                				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
                		);
                		
                		System.out.println();
                		dogCount++;
            		}
            	}
            	
            	System.out.println();
            	
            	if (dogCount == 0) {
            		System.out.println(YELLOW + "[WARNING]" + RESET + " No available dogs");
            	}
            	
            	System.out.println("Total dogs: " + dogCount);
            	
            	System.out.println();
            	printCenteredTitle(" AVAILABLE MONKEYS ", 90);
            	System.out.println();
            	System.out.printf(MONKEY_FORMAT, "Name", "Status", "Acq. Country", "Reserved", "Service Country"); // Print in monkey format with these column names
            	System.out.println();
            	printDivider("large");
            	System.out.println();
            	
            	for (Monkey monkey: monkeyList) {
            		trainingStatus = monkey.getTrainingStatus();
            		reserved = monkey.getReserved();
            		if ((trainingStatus.equalsIgnoreCase("In-Service")) && (!reserved)) {
            			name = monkey.getName();
                		acquisitionCountry = monkey.getAcquisitionLocation();
                		inServiceCountry = monkey.getInServiceCountry();
                		
            			System.out.printf(MONKEY_FORMAT,
                				(name.isEmpty() ? "None": name),
                				(trainingStatus.isEmpty() ? "None": trainingStatus),
                				(acquisitionCountry.isEmpty() ? "None": acquisitionCountry),
                				(reserved ? "Reserved": "Not reserved"),
                				(inServiceCountry.isEmpty() ? "None": inServiceCountry)
                		);
                		
                		System.out.println();
                		monkeyCount++;
            		}
            	}
            	
            	System.out.println();
            	
            	if (monkeyCount == 0) {
            		System.out.println(YELLOW + "[WARNING]" + RESET + " No available monkeys");
            	}
            	
            	System.out.println("Total monkeys: " + monkeyCount);
            	System.out.println();
            	break;
            	
            default: // List type does not match any of the above
            	
            	System.out.println(RED + "[ERROR]" + RESET + " Invalid list type");
            	
            }

        }
}