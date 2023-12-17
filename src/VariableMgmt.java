import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This will store variable that are chosen so that it can be called again at a later time.
 * @Author Omar M. Radwan
 * @version 1.0.0
 */
public class VariableMgmt {
    private Dictionary<String, String> currVars = new Hashtable<>();

    public VariableMgmt(){}

    /**
     * Adds a variable to the Dictionary if it not in there already
     * @param key - The key to add to the dictionary
     * @param value - The value to add to the dictionary
     */
    public void addVar(String key, String value) {
        if (!checkVar(key)) {
            currVars.put(key, value);
        }
    }

    /**
     * Returns a value based on the key or an empty string if the key doesn't exist
     * @param key - The key you need from the dictionary
     * @return The value or empty string if key doesn't exist
     */
    public String getVar(String key) {
        if (!(checkVar(key))) {
            return "";
        } else {
            return currVars.get(key);
        }
    }

    /**
     * Checks to see if the key exists in the dictionary
     * @param key - The key to check
     * @return True if it does exist, false if it does not
     */
    public boolean checkVar(String key) {
        return currVars.get(key) != null;
    }

    /**
     * Checks to see if a value is already used by another variable
     * @param value - The value to check
     * @return True if it does find the value, false if it does not
     */
    public boolean checkValue(String value) {
        boolean hasValue = false;
        Enumeration<String> keys = currVars.keys();
        while(keys.hasMoreElements()) {
            if (getVar(keys.nextElement()).equals(value)) {
                hasValue = true;
            }
        }
        return hasValue;
    }
}
