
/**
 * Date.java - An abstract class which represents a day in a year
 * <p>Problem Statement: A class which stores the details of a particular date.</p>
 *                 
 * Instance Variables:<ul>
 *  <li> String <code><b>month</b></code> - The month of the date </li>
 *  <li> String <code><b>day</b></code> - The day of the date </li>
 *  <li> String <code><b>year</b></code> - The year of the date </li>
 *  <li> String <code><b>date</b></code> - The full formatted date </li></ul>
 * 
 * @author Wes Parker
 * @version Module 16, Final Project
 */


//This shows the use of abstract classes
public abstract class Date
{
    private String month = "00" ;       //The month of the date
    private String day = "00" ;         //The day of the date
    private String year = "0000" ;      //The year of the date
    private String date = "00/00/00" ;  //The full formatted date 
    
    /**
     * No argument constructor sets all values to 0
     */
    public Date() {
        month = "00" ;
        day = "00" ;
        year = "0000" ;
        date = "00/00/00" ;
    }
    
    /**
     * full constructor sets each instance variable to an argument
     * @param month The month to be assigned to the Date Object
     * @param day The day to be assigned to the Date Object
     * @param year The year to be assigned to the Date Object
     */
    public Date(String month, String day, String year) {
        this.month = month ;
        this.day = day ;
        this.year = year ;
        date = month + "/" + day + "/" + year ;
    }
    
    /**
     * Getter for month
     * @return The value of the month variable for this Date object
     */
    public String getMonth() {
        return month ;
    }
    
    /**
     * Getter for day
     * @return The value of the day variable for this Date object
     */
    public String getDay() {
        return day ;
    }
    
    /**
     * Getter for year
     * @return The value of the year variable for this Date object
     */
    public String getYear() {
        return year ;
    }
    
    /**
     * Getter for date
     * @return The value of the date variable for this Date object
     */
    public String getDate() {
        return date ;
    }
    
    /**
     * Abstract method to return Draft number
     */
    public abstract int getDraftNumber() ;
    
    /**
     * Setter for month
     * @param month a month to replace the current month for a Date object
     */
    public void setMonth(String month) {
        this.month = month ;
        setDate(month, day, year) ;
    }
    
    /**
     * Setter for day
     * @param day a day to replace the current day for a Date object
     */
    public void setDay(String day) {
        this.day = day ;
        setDate(month, day, year) ;
    }
    
    /**
     * Setter for year
     * @param year a year to replace the current year for a Date object
     */
    public void setYear(String year) {
        this.year = year ;
        setDate(month, day, year) ;
    }
    
    /**
     * Setter for date
     * @param month a month to replace the current month for a Date object
     * @param day a day to replace the current day for a Date object
     * @param year a year to replace the current year for a Date object
     */
    public void setDate(String month, String day, String year) {
        this.month = month ;
        this.day = day ;
        this.year = year ;
        date = month + "/" + day + "/" + year ;
    }
    
    /**
     * Returns a Date object as a String
     * @return a String which tells the date
     */
    public String toString() {
        return "The date is: " + date ;
    }

    /**
     * Compares the instance variables as a test for equality between 2 Dates
     * after first checking that the argument is not null and is of the Date Class
     * @param another The Date to be compared to this Date
     * @return true if the calling Date object has the same month, day, and year as the Date 
     * object passed to the parameter
     */
    public boolean equals(Object another) {
        if (another != null && another.getClass() == getClass()){
            Date otherDate = (Date) another ;
            return date.equals(otherDate.date) ;
        }
        
        return false ;
    }
}
