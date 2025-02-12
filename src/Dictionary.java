import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary{
    private AVLTree<String> dict;
    public Dictionary(String s){ //creating dictionary with a given word.
        dict = new AVLTree<>();
        dict.insertAVL(s.toLowerCase());
    }
    public Dictionary(){
        dict = new AVLTree<>();
    } //creating an empty dictionary.
    public Dictionary(File f){ //creating dictionary with a given file.
        try {
            dict = new AVLTree<>();
            Scanner input = new Scanner(f);
            while(input.hasNext()) {
                String word = input.next().toLowerCase().strip();
                if(!dict.isInTree(word))
                    dict.insertAVL(word);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    public void display(){
        dict.inorder(); //printing the dictionary tree's nodes (words) in inorder traversal.
        System.out.println();
    }
    

    public void addWord(String s) throws WordAlreadyExistsException{
        if(!this.dict.isInTree(s.toLowerCase())) { //if the word exist in the tree.
            this.dict.insertAVL(s.toLowerCase());
            System.out.println("Word '" + s + "' added successfully.");
        }
        //else, throw an exception.
        else throw new WordAlreadyExistsException("Word '" + s + "' already exists.");
    }

	public void deleteWord(String s) throws WordNotFoundException{
        if(this.dict.isInTree(s.toLowerCase())) { //if the word exist in the tree.
            this.dict.deleteAVL(s.toLowerCase());
            System.out.println("Word '" + s + "' deleted successfully.");
        }
        //else, throw an exception.
        else throw new WordNotFoundException("Word '" + s + "' not found.");
    }
    
    public boolean findWord(String s){return this.dict.isInTree(s.toLowerCase());} //use the method built-in with the avltree.

    

    private boolean isSimilar(String searched, String compared) {
        searched = searched.toLowerCase(); //the word we want to find words that are similar to it.
        compared = compared.toLowerCase(); //the word that we'll compare it with our word.

        //instantiating variables to refer to their indexes.
        int i = 0;
        int j = 0;

        //a boolean that indicate that we found one difference between our and compared words.
        boolean flag = false;

        //if both words differ in length by more than 1, immediately return false.
        if(searched.length() - compared.length() > 1 || searched.length() - compared.length() < -1) return false;

        //if both words are identical, immediately return false.
        if(searched.compareTo(compared) == 0) return false;

        //keep iterating until one of them reaches maximum.
        while (i < searched.length() && j < compared.length()) {

            //if both characters are identical, we increment their index iterators.
            if (searched.charAt(i) == compared.charAt(j)) {
                i++;
                j++;
            }

            //if we haven't detected any difference yet.
            else if (!flag) {
                flag = true; //indicate that we found a difference.

                //if the length of our word is longer that the compared one, increment our word index iterator while the other remains same.
                if (searched.length() > compared.length()) i++;

                //is it is the opposite, increment the compared word index iterator while ours remains same.
                else if (searched.length() < compared.length()) j++;

                //if they are having the same length but differ in one letter in some index.
                else {
                    i++;
                    j++;
                }
            } 

            //if we found second difference, return false.
            else {
                return false;
            }
        }
    
        return true;
    }

    public String findSimilar(String s) { //helper method.
        String result = findSimilar(this.dict.root, s);
        if(result.isEmpty()) return "There is no word similar to " + s + "."; //if there is no similar word.
        else return result.substring(0, result.length()-2) + ".";
    }

    private String findSimilar(BSTNode<String> root, String word) {
        if(root == null) return""; //base case if the node is empty.

        //apply isSimilar to current word, if it is similar, return the word with its children, otherwise return its children only.
        return isSimilar(word, root.el) ? root.el + ", " + findSimilar(root.right, word) + findSimilar(root.left, word) : findSimilar(root.right, word) + findSimilar(root.left, word);
    }

    public void save(String name){
        File file = new File(name); //creating a file with the name given.
        try{
        PrintWriter output = new PrintWriter(file); //creating PrintWriter object to write the words in the new file.
        writer(this.dict.root, output); //write all the words in the dictionary to the file.
        output.close(); //closing the PrintWriter object.
        }catch(Exception e){System.out.println(e.getMessage());}
        
    }

    private void writer(BSTNode<String> root, PrintWriter output){
        if(root == null) return; //base case where the node is empty.

        //write the word and its children.
        output.println(root.el);
        writer(root.left, output);
        writer(root.right, output);
    }
}