/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatecookbook;

// Imports for I/O
import java.io.*;
import java.util.Arrays;

// Import for iteration
import java.util.Iterator;

// Imports for JSON
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 *
 * @author Ethan Stewart
 */
public class Recipe {
    // MEMBER VARIABLES
    private String title;
    private String[] ingredients;
    private String directions;
    private static final String[] staples = {
        "milk",
        "butter",
        "eggs",
        "salt",
        "pepper",
        "flour"
    };
    
    // DEFAULT CONSTRUCTOR (VERY USELESS)
    Recipe() {
        title = "";
        ingredients = new String[1];
        directions = "";
    }
    
    // CUSTOM CONSTRUCTORS

    /**
     * Custom constructor to initialize all variables
     * @param t
     * @param ing
     * @param d
     */
    public Recipe(String t, String[] ing, String d) {
        title = t;
        ingredients = ing;
        directions = d;
    }
    
    // GETTERS
    public String   getTitle()       { return title;       }
    public String[] getIngredients() { return ingredients; }
    public String   getDirections()  { return directions;  }
    public String[] getStaples()     { return staples;     }
    
    public String getIngredientsAsString() {
        String returnString = "";
        for (String ingredient : this.ingredients) {
            returnString += ingredient + "\n";
        }
        
        return returnString;
    }
    
    // SETTERS
    // Set the recipe's title
    public void setTitle(String t) {
        title = t;
    }
    
    // Set all ingredients
    public void setAllIngredients(String[] ing) {
        ingredients = ing;
    }
    
    // Set one specific ingredient (knowledge of existing array required)
    public void setOneIngredient(String ing, int i) {
        if (i < ingredients.length) {
            ingredients[i] = ing;
        }
        else {
            // i is larger than last index
            // Add error message here
        }
    }
    
    // Set the directions
    public void setDirections(String d) {
        directions = d;
    }
    
    // Overloaded to allow for appending
    public void setDirections(String d, boolean append) {
        if (append) {
            directions += d;
        }
        else {
            directions = d;
        }
    }
    
    // METHODS
    public String printableRecipe() {
        // Start the printable string with the title
        String printable = this.title + "\n\n";
        
        // Add the ingredients to printable
        printable += this.getIngredientsAsString() + "\n";
        
        // Add the directions and return printable
        printable += this.directions;
        
        return printable;
    }
    
    public void writeRecipeToFile() {
        // Create the filename
        String name = this.title;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ') {
                name = name.substring(0, i) + '_' + name.substring(i+1);
            }
        }
        String filename = "resources/recipes/" + name.toLowerCase() + ".json";
        
        // Create the JSON
        JSONObject json = new JSONObject();
        json.put("title", this.title);
        json.put("directions", this.directions);
        
        JSONArray ingArray = new JSONArray();
        for (String ingredient : this.ingredients) {
            ingArray.add(ingredient);
        }
        json.put("ingredients", ingArray);
        
        
        
        // Attempt to write the JSON to the file
        try {
            // Open the file writer and wrap in buffer
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            
            // Write the data to the file
            bw.write(json.toJSONString());
            
            // Close the file
            bw.close();
        }
        
        // Catch any IOExceptions
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *
     * @param filename
     * @return Recipe
     */
    public static Recipe readRecipeFromFile(String filename) {
        // Create the parser instance
        JSONParser parser = new JSONParser();
        
        FileReader fr;
        String[] blank = new String[1];
        blank[0] = "ERR";
        Recipe readRecipe = new Recipe("ERR", blank, "ERR");
        
        try {
            // Read the file
            fr = new FileReader(filename);
            
            // Parse the contents of the file
            Object obj = parser.parse(fr);
            JSONObject json = (JSONObject) obj;
            
            // Grab the individual attributes
            String t = (String) json.get("title");
            String d = (String) json.get("directions");
            String[] ing;
            
            JSONArray ingJ = (JSONArray) json.get("ingredients");
            Iterator<String> it = ingJ.iterator();
            int count = 0;
            while (it.hasNext()) {
                count++;
                it.next();
            }
            
            ing = new String[count];
            it = ingJ.iterator();
            
            for (int i = 0; i < count; i++) {
                ing[i] = (String) ingJ.get(i);
            }
            
            // Set the attributes of the read recipe
            readRecipe.setTitle(t);
            readRecipe.setAllIngredients(ing);
            readRecipe.setDirections(d);
            
            fr.close();
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Return the recipe
        return readRecipe;
    }
    
    public static Recipe[] readAllRecipes() {
        // Loop through all files in resources/recipes
        String dir = "resources/recipes";
        File folder = new File(dir);
        File[] allFiles = folder.listFiles();
        Recipe[] returnArray = new Recipe[allFiles.length];
        
        for (int i = 0; i < allFiles.length; i++) {
            // Make sure the file is a file and has the json suffix
            File file = allFiles[i];
            if (file.isFile() && file.getName().endsWith(".json")) {
                // Read the recipe in
                Recipe r = Recipe.readRecipeFromFile(dir + "/" + file.getName());
                
                // Store the recipe in returnArray
                returnArray[i] = r;
            }
        }
        
        return returnArray;
    }
    
}
