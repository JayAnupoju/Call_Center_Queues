import itsc2214.*;
/**
 * Project 3 - Call class, used to model a caller
 * into a call center. It stores name of the caller,
 * VIP status and duration of the call.
 * 
 * @author  Jay Anupoju
 */
public class Call 
{
    /**
     * declare instance variables for name, vip, 
     * and duration.
     */

    private String callerName;
    private boolean vip;
    private int duration;

    /**
     * Initializes callerName to "Unknown Caller".
     */
    public Call()
    {

        this.callerName = "Unknown Caller";
        this.vip = false;
        this.duration = 10;
    }

    /**
     * Constructor that initializes all instance variables
     * with values passed as parameters.
     * @param caller name of the caller
     * @param vip boolean true if caller is VIP, false otherwise
     * @param duration length of the call in fake units
     */
    public Call(String caller, boolean vip, int duration)
    {
        if (caller == null || caller.trim().isEmpty()) {
            this.callerName = "Unknown Caller";
        } else {
            this.callerName = caller;
        }

        this.vip = vip;
        
        // Ensure call duration is at least 1 minute
        if (duration <= 0) {
            this.duration = 1;
        } else {
            this.duration = duration;
        }
    }

    /**
     * Returns the name of the caller.
     * @return caller name
     */
    public String getName()
    {
        return callerName;
    }

    /**
     * Returns true if the caller is a VIP, false otherwise.
     * @return true if VIP, false otherwise
     */
    public boolean isVIP()
    {
        return vip;
    }

    /**
     * Returns the remaining duration of the call.
     * @return duration of the call
     */
    public int getDuration()
    {
        return duration;
    }

    /**
    * Decreases the call duration by 1, if it's greater than 0.
    */
    public void decrement(){
        if (duration > 0) {
            duration--;
        }
    }
}
