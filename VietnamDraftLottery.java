import java.io.File ;
import java.io.FileNotFoundException ;
import java.util.Scanner ;

/**
 * VietnamDraftLottery.java - Tells a user if they would've been drafted into Vietnam using their birthday.
 * <p>Problem Statement: Prompts the user for their birthday, then compares that date to an array of Dates containing draft information populated by a text file.</p>
 *                 
 * Algorithm:<ol>
 *  <li>Populate an Array of Date objects using populateDateArray(). </li>
 *  <li>Print a header decribing the purpose of the program. </li>
 *  <li>Use a <code><b>while</b></code> until the user chooses to quit. </li>
 *  
 *      In the loop:<ul>
 *      <li>Prompt for a Date to be searched for. </li>
 *      <li>Use dateToIndex() to convert and store the date to an index in the array. </li>
 *      <li>Print the toString() for the object at that index.</li>
 *      <li>Use findClosestAlternate() to print the closest date that would've yeilded a different result.</li>
 *      <li>Ask the user if they wish to continue.</li></ul>
 *      
 *  <li>Print goodbye message to user. </li>
 *  
 * @author Wes Parker
 * @version Module 16, Final Project
 */

public class VietnamDraftLottery
{
    public static void main(String[] arg) {
        //Scanner of input
        Scanner keyboard = new Scanner(System.in) ;
        //Array of Dates which contains draft information on all birthdays
        Date draftDates[] = populateDateArray("Draft Numbers.txt") ;

        String birthday = "" ;      //The user's birthday to find in the array
        int bDayAsIndex = 0 ;       //The index of the user's birthday in the array
        boolean loopAgain = true ;  //Determines if the program will keep prompting for dates

        //Print Header info
        System.out.println("In 1969, the U.S. government held two draft lotteries which determined who would be drafted into the Vietnam war.") ;
        System.out.println("Each day of the year (including February 29th) was given a draft number which determined the order that men would be called into service in.") ;
        System.out.println("All men ages 18-26 who did not have deferments were expected to report for service when their birthdate was called.") ;
        System.out.println("Draft numbers 1-195 were called.") ;

        System.out.print("\nTo find out if you would've been drafted, enter your birthday... ") ;

        while (loopAgain) {
            //Prompt for date to check
            System.out.print("\nEnter a date (MM/DD): ") ;
            birthday = keyboard.nextLine() ;

            //Translate date to index
            bDayAsIndex = dateToIndex(birthday, draftDates) ;

            //Call the toString method for the object in the array that corresponds to the birthday entered
            System.out.println(draftDates[bDayAsIndex].toString() + " Your Draft Number was " + draftDates[bDayAsIndex].getDraftNumber() + ".") ;

            //Print the closest alternative outcome date
            System.out.println(findClosestAlternate(bDayAsIndex, draftDates)) ;

            //Prompt for another cycle of the loop
            System.out.print("\nType \"yes\" if you would like to enter another date: ") ;
            if (!keyboard.nextLine().trim().toLowerCase().equals("yes")) {
                loopAgain = false ;
            }
        }

        //Close input stream
        keyboard.close() ;
        
        //Print goodbye message when user exits loop
        System.out.print("\nGoodbye Mr. Merrill, I'll see you next semester.") ;
    }

    /**
     * Populates an array of Date objects using input from a file
     * @param inputFileName a String object representing a file name to be used for input
     * @return an array of Date objects which are assigned as DateDrafted or DateNotDrafted objects
     */
    //This shows Text File input
    public static Date[] populateDateArray(String inputFileName) {
        try{
            File inputFile = new File(inputFileName) ;      //Instantiates input file as a File object
            Scanner input = new Scanner(inputFile) ;        //Scanner for inputFile

            Date dates[] = new Date[366] ;                  //Date array to be returned must have length 366 to account for entire year

            //Fill the array
            for (int i = 0; i < 366; i++) {
                String date = input.next();             //The date formatted as 'MM/DD'
                String[] dateParts = date.split("/");   //split the date into month and day
                String month = dateParts[0];            
                String day = dateParts[1];              
                int draftNumber = input.nextInt() ;     //The draftNumber associated with the Date
                String status = input.next();           //Determines if the date was a drafted date or not

                //If the date was drafted create a DateDrafted object
                //otherwise create a DateNotDrafted object
                //This shows the use of polymorphism
                if (status.equals("D")){
                    dates[i] = new DateDrafted(month, day, draftNumber) ;
                } else {
                    dates[i] = new DateNotDrafted(month, day, draftNumber) ;
                }
            }

            //Close input file and return Date array
            input.close() ;
            return dates ;
        }
        //Handles 'FileNotFound' Exception
        catch (FileNotFoundException e) {
            System.out.print(e.getMessage()) ;
            return null ;
        }
    }

    /**
     * Converts a date to an index in an array
     * @param birthday a String object representing a date formatted as (MM/DD) or (MM-DD)
     * @param draftDates an array of Dates which contains the index we wish to return
     * @throws InvalidDateException dates which are not in the calandar year or in the format (MM/DD)
     * @return an index in the array of Dates for the date passed ex. 02/20 returns 50 because it's the 51st day of the year/index in the array
     */
    public static int dateToIndex(String birthday, Date[] draftDates) {
        try{
            //Throw exception for any dates of the wrong length
            if (birthday.length() != 5) {
                throw new InvalidDateException() ;
            }

            //Searches the Array until there's a match and returns the index of the match
            for (int i = 0; i < draftDates.length; i++) {
                if (draftDates[i].getMonth().equals(birthday.substring(0, 2)) && draftDates[i].getDay().equals(birthday.substring(3))) {
                    return i;
                }
            }

            //Throw exception if date was invalid
            throw new InvalidDateException() ;
        }
        //This shows the use of exceptions
        //This is also an example of recursion
        catch (InvalidDateException e) {
            Scanner keyboard = new Scanner(System.in) ;

            //If date entered was invalid, prompt for new date
            System.out.print(e.getMessage()) ;
            birthday = keyboard.nextLine() ;

            //Translate date to index
            return dateToIndex(birthday, draftDates) ;
        }
    }

    /**
     * Tells how far off the user was in days from an alternate outcome 
     * ex. if user wasn't drafted, this method will look for the closest date when the user would've been drafted
     * @param currentDate an int which is the index of the date the user selected
     * @param draftDates an array of Dates to be searched for the nearest alternate outcome
     * @return a String which tells the user how many days earlier/later they would've had to have been born to have been drafted or not drafted
     */
    public static String findClosestAlternate(int currentDate, Date[] draftDates) {
        int laterDate = currentDate ;       //the distance from the index of the date selected to the index of the closest date afterwards which has the opposite outcome
        int earlierDate = currentDate ;     //the distance from the index of the date selected to the index of the closest date afterwards which has the opposite outcome
        boolean doneSearching = false ;     //evaluates to true once the closest index of the alternative has been found

        //If the user was drafted
        if (draftDates[currentDate].getClass().getSimpleName().equals("DateDrafted")) {
            //Find the closest later date when the user wouldn't have been drafted
            while (!doneSearching) {
                //Outer if-statement ensures we don't go out of bounds otherwise set laterDate to a value that ensures it's not less than earlierDate
                if (laterDate + 1 <= draftDates.length - 1) {
                    if (draftDates[++laterDate].getClass().getSimpleName().equals("DateNotDrafted")) {
                        doneSearching = true ;
                    }
                } else {
                    laterDate = 99999 ;
                    doneSearching = true ;
                }
            }

            //reset doneSearching
            doneSearching = false ;

            //Find the closest prior date when the user wouldn't have been drafted
            while (!doneSearching) {
                //Outer if-statement ensures we don't go out of bounds otherwise set earlierDate to a value that ensures it's not less than laterDate
                if (earlierDate - 1 >= 0){
                    if (draftDates[--earlierDate].getClass().getSimpleName().equals("DateNotDrafted")) {
                        doneSearching = true ;
                    }
                } else {
                    earlierDate = 99999 ;
                    doneSearching = true ;
                }
            }

            //Return a message with the closest date where the user wouldn't have been drafted with alternate cases for plurality
            if ((Math.abs(laterDate - currentDate)) < (Math.abs(currentDate - earlierDate))) {
                return ((laterDate - currentDate) != 1) ? "If you were born " + (laterDate - currentDate) + " days later, you wouldn't have been drafted." : "If you were born 1 day later, you wouldn't have been drafted.";
            } else if ((Math.abs(laterDate - currentDate)) > (Math.abs(currentDate - earlierDate))) {
                return ((currentDate - earlierDate) != 1) ? "If you were born " + (currentDate - earlierDate) + " days earlier, you wouldn't have been drafted." : "If you were born 1 day earlier, you wouldn't have been drafted.";
            } else {
                return ((currentDate - earlierDate) != 1) ? "If you were born " + (laterDate - currentDate) + " days earlier or later, you wouldn't have been drafted." : "If you were born 1 day earlier or later, you wouldn't have been drafted." ;
            }

        } else {

            //If the user wasn't drafted
            //Find the closest later date when the user would have been drafted
            while (!doneSearching) {
                //Outer if-statement ensures we don't go out of bounds otherwise set laterDate to a value that ensures it's not less than earlierDate
                if (laterDate + 1 <= draftDates.length - 1) {
                    if (draftDates[++laterDate].getClass().getSimpleName().equals("DateDrafted")) {
                        doneSearching = true ;
                    }
                } else {
                    laterDate = 99999 ;
                    doneSearching = true ;
                }
            }

            //reset doneSearching
            doneSearching = false ;

            //Find the closest prior date when the user would have been drafted
            while (!doneSearching) {
                //Outer if-statement ensures we don't go out of bounds otherwise set earlierDate to a value that ensures it's not less than laterDate
                if (earlierDate - 1 >= 0){
                    if (draftDates[--earlierDate].getClass().getSimpleName().equals("DateDrafted")) {
                        doneSearching = true ;
                    }
                } else {
                    earlierDate = 99999 ;
                    doneSearching = true ;
                }
            }

            //Return a message with the closest date where the user would have been drafted with alternate cases for plurality
            if ((Math.abs(laterDate - currentDate)) < (Math.abs(currentDate - earlierDate))) {
                return ((laterDate - currentDate) != 1) ? "If you were born " + (laterDate - currentDate) + " days later, you would have been drafted." : "If you were born 1 day later, you would have been drafted.";
            } else if ((Math.abs(laterDate - currentDate)) > (Math.abs(currentDate - earlierDate))) {
                return ((currentDate - earlierDate) != 1) ? "If you were born " + (currentDate - earlierDate) + " days earlier, you would have been drafted." : "If you were born 1 day earlier, you would have been drafted.";
            } else {
                return ((currentDate - earlierDate) != 1) ? "If you were born " + (laterDate - currentDate) + " days earlier or later, you would have been drafted." : "If you were born 1 day earlier or later, you would have been drafted." ;
            }
        }
    }
}
