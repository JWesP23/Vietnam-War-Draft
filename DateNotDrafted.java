
/**
 * DateNotDrafted.java - Represents a birthdate that wouldn't have been drafted.
 * <p>Problem Statement: A class that stores the draft number of a date which was not drafted.</p>
 *                 
 * Instance Variables:<ul>
 *  <li> int <code><b>draftNumber</b></code> - The draft number that was assigned to this date </li></ul>
 * 
 * @author Wes Parker
 * @version Module 16, Final Project
 */


//This class shows the use of inheritance
public class DateNotDrafted extends Date
{
    private int draftNumber = 0 ; //The number that this date was picked
    
    /**
     * No argument constructor sets all values to 0
     */
    public DateNotDrafted() {
        super() ;
    }
    
    /**
     * full constructor sets each instance variable to an argument
     * @param month The month to be assigned to the Object
     * @param day The day to be assigned to the Object
     * @param draftNumber The draft number to be assigned to the Object
     */
    public DateNotDrafted(String month, String day, int draftNumber) {
        super(month, day, "0000") ;
        this.draftNumber = draftNumber ;
    }
    
    /**
     * Getter for draft number
     * @return The value of the draftNumber variable for this object
     */
    public int getDraftNumber() {
        return draftNumber ;
    }
    
    /**
     * Returns a DateNotDrafted object as a String
     * @return a String which tells the date and that the user would not be drafted
     */
    public String toString() {
        return "You and everyone else born on " + getMonth() + "/" + getDay() + " would not have been drafted." ;
    }
}
