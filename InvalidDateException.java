
/**
 * InvalidDateException.java - An Exception class for a DateDrafted or DateNotDrafted object.
 * <p>Problem Statement: An exception to be thrown when attempting to input a date with a format other than MM/DD.
 *                 
 * @author Wes Parker
 * @version Module 16, Final Project
 */

//This class shows the use of Exceptions
public class InvalidDateException extends Exception
{
    /**
     * No-arg Constructor sets message to "Incorrect coin value entered."
     */
    public InvalidDateException(){
        super("Please enter a valid date in the correct format; date must be entered as \"MM/DD\": ") ;
    }
    
    /**
     * String Constructor sets message to the String passed
     * @param message a String to be stored as the message for the exception
     */
    public InvalidDateException(String message){
        super(message) ;
    }
}
