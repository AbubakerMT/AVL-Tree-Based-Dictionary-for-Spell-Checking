import java.io.File;
import java.util.Scanner;

public class mainProgram {
    static Scanner input = new Scanner(System.in);
    static Dictionary dict;
    public static void main(String[] args) {
        String choice;
        System.out.println("Welcome to the dictionary!.");
        System.out.println("-".repeat("Welcome to the dictionary!.".length()));
        mainProgram.create();
        while(true){
            System.out.println("Dictionary menu. Please enter the number that correspond to the method you want.");
            System.out.println("-".repeat("Dictionary menu. Please enter the number that correspond to the method you want.".length()));
            System.out.println("1- Add word to the dictionary.");
            System.out.println("2- Delete word from the dictionary.");
            System.out.println("3- Find similar words to a specific one in the dictionary.");
            System.out.println("4- Display the current dictionary.");
            System.out.println("5- Check if a word exist.");
            System.out.println("6- Exit the program.");
            while(true) {
                choice = input.nextLine();
                if (choice.equals("1")) {
                    try {
                        mainProgram.add();
                        System.out.println();
                        break;
                    }catch(WordAlreadyExistsException e){
                        System.out.println(e.getMessage());
                        System.out.println();
                        break;
                    }
                }
                else if (choice.equals("2")) {
                    try {
                        mainProgram.delete();
                        System.out.println();
                        break;
                    }catch(WordNotFoundException e){
                        System.out.println(e.getMessage());
                        System.out.println();
                        break;
                    }
                }
                else if (choice.equals("3")) {
                    mainProgram.similar();
                    System.out.println();
                    break;
                }
                else if(choice.equals("4")){
                    dict.display();
                    System.out.println();
                    break;
                }
                else if(choice.equals("5")){
                    mainProgram.search();
                    System.out.println();
                    break;
                }
                else if (choice.equals("6")) break;
                else System.out.println("Please select between one of the given options.");
            }
            if(choice.equals("6")) {
                mainProgram.ending();
                break;
            }
        }
        input.close();
    }
    public static void create(){
        System.out.println("Please enter the number that corresponds to how would you like to create the dictionary.");
        System.out.println("-".repeat("Please enter the number that corresponds to how would you like to create the dictionary.".length()));
        System.out.println("1- Create empty dictionary.");
        System.out.println("2- Create a dictionary with a given word.");
        System.out.println("3- Create a dictionary with a given file.");
        while(true){
            String choice = input.nextLine();
            if(choice.equals("1")){
                dict = new Dictionary();
                System.out.println("Dictionary created successfully!\n");
                break;
            }
            else if (choice.equals("2")){
                System.out.println("Please enter the word.");
                String name = validName();
                dict = new Dictionary(name);
                System.out.println("Dictionary created successfully!");
                break;
            }
            else if (choice.equals("3")){
                System.out.println("Please enter the file name.");
                    String name = input.nextLine();
                    if(!name.endsWith(".txt")) name+=".txt";
                    File file = new File("src\\"+name);
                    while(!file.exists()){
                        System.out.println("Please enter an existed file name, or if you don't have one, enter '-'.");
                        name = input.nextLine();
                        if(name.equals("-")) break;
                        if(!name.endsWith(".txt")) name+=".txt";

                        file = new File("src\\"+name);
                    }
                    if(name.equals("-")){
                        mainProgram.create();
                        break;
                    }
                    System.out.println("Words are being retrieved from the file, please wait patiently.\n");
                    dict = new Dictionary(file);
                    System.out.println("Dictionary created successfully!\n");
                    break;
            }
            else System.out.println("Please select between one of the given options.");
        }
    }
    public static void add() throws WordAlreadyExistsException {
        System.out.println("Please enter the word you'd like to add to the dictionary.");
        String word = validName();
        dict.addWord(word);
    }
    public static void delete() throws WordNotFoundException{
        System.out.println("Please enter the word you want to delete.");
        String word = validName();
        dict.deleteWord(word);
    }
    public static void similar(){
        System.out.println("Please enter the word you want to find its similars.");
        String word = validName();
        System.out.println(dict.findSimilar(word));
    }
    public static  void search(){
        System.out.println("Please enter the word to search for.");
            String word = validName();
            if(dict.findWord(word)) System.out.println("Word '"+ word+ "' does exist in the dictionary.");
            else System.out.println("Word '"+ word+ "' does not exist in the dictionary.");
        }
    public static void ending(){
        System.out.println("Would you like to save the dictionary? [Y/N]");
        String choise = input.nextLine().toLowerCase();
        while(!choise.equals("y") && !choise.equals("n")) {
            System.out.println("Please enter either y or n");
            choise = input.nextLine();
        }
        if(choise.equals("y")) {
            System.out.println("Please enter the name of the dictionary.");
            String name = input.nextLine();
            while(name.matches(".*[\\\\/:*?\"<>|].*") || name.isEmpty()){
                System.out.println("Name of the file can't be empty neither has one of these illegal characters '\\/:*?\"<>|'.");
                name = input.nextLine();
            }
            if (name.endsWith(".txt")) dict.save(name);
            else dict.save(name+".txt");
            System.out.println("Dictionary saved successfully!");
        }
        System.out.println("Thank you for using our program!");
    }
    public static String validName(){
        String choice = input.nextLine();
            while(!choice.matches("^[a-zA-Z]+$")){
                System.out.println("Word can neither be empty nor contains space or special characters.");
                System.out.println("Please enter a valid word.");
                choice = input.nextLine();
            }
        return choice;
    }
}
