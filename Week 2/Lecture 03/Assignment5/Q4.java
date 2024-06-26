package Assignment5;
import java.util.*;

public class Q4 {
    public static void main(String args[]) {
        HashMap<Integer, String> hash_map = new HashMap<Integer, String>();
        hash_map.put(1, "Red");
        hash_map.put(2, "Green");
        hash_map.put(3, "Black");
        hash_map.put(4, "White");
        hash_map.put(5, "Blue");
        
        // Print the original map
        System.out.println("The Original map: " + hash_map);
        
        // Create a shallow copy of the original map
        HashMap<Integer, String> new_hash_map = (HashMap<Integer, String>) hash_map.clone();
        new_hash_map.put(6, "Cyan");
        
        // Print the cloned map and the original map after cloning
        System.out.println("Cloned map: " + new_hash_map);
        System.out.println("Original after Clone: " + hash_map);

        // Compare the original and cloned maps
        boolean areEqual = hash_map.equals(new_hash_map);
        System.out.println("Are the original and cloned maps equal? " + areEqual); //false because the data cloned maps is updated
    }
}
