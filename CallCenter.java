import itsc2214.*;
/**
 * Project 3 CallCenter class, it implements (for a simulation)
 * a call center, keeping track of incoming calls and answering
 * calls at the front of the queue when it their time.
 * 
 * @author Jay Anupoju
 * @version Jul 29, 2025
 */
public class CallCenter
{
    /**
     * Instance variables for this class.
     * Define an array for the call queue,
     * and instance variable for the call
     * that is on the line, and counters
     * for the clock (time) and for the
     * number of elements in the queue.
     */

    private Call[] callQueue;   // Array to store incoming calls in the queue
    private Call callOnTheLine; // The call currently being handled
    private int clock;          // Tracks the current time or simulation clock
    private int numCalls;       // Total number of calls received or processed
 

    /**
     * Default constructor for the CallCenter, initializes
     * all instance variables with default values.
     */
    public CallCenter()
    {
        this.callQueue = new Call[10];  // Initialize call queue with capacity for 10 calls
        this.callOnTheLine = null;      // No call is currently being handled
        this.clock = 0;                 // Start the simulation clock at 0
        this.numCalls = 0;              // Initialize the number of calls to 0
    }

    /**
     * Adds a call to the call queue.
     * 
     * @param n name of the caller.
     * @param v vip status of the caller.
     * @param d during of this call.
     * @return true if the call is added to the call queue, 
     * false otherwise.
     */
    public boolean addCall(String n, boolean v, int d)
    {
        // Validate input: name must be non-null and non-empty; duration must be positive
        if (n == null || n.isEmpty() || d <= 0) {
            throw new IllegalArgumentException("Invalid name or duration.");
        }
        
        // If the queue is full, return false
        if (numCalls == callQueue.length) { 
            return false;
        }

        // Create a new Call object with provided parameters
        Call newCall = new Call(n, v, d);

        if (v) {
            // Shift all existing calls one position to the right to make room at the front
            for (int i = numCalls; i > 0; i--) {
                callQueue[i] = callQueue[i - 1];
            }
            callQueue[0] = newCall;
        } else {
            // Add regular call to the end of the queue
            callQueue[numCalls] = newCall;
        }

        numCalls++; 
        return true;
    }

    /**
     * Simulates answering the call from the call
     * in the front of the queue.
     * @return null if an error is found, or the call 
     * object if answered.
     */
    public Call answerCall()
    {
        // Return null if already handling a call or if no calls are in the queue
        if (isBusy() || numCalls == 0) {
            return null;
        }

        // Assign the first call in the queue to be the current call on the line
        callOnTheLine = callQueue[0];

        // Shift remaining calls forward in the queue
        for (int i = 1; i < numCalls; i++) {
            callQueue[i - 1] = callQueue[i];
        }

        callQueue[numCalls - 1] = null; // Clear the last slot 
        numCalls--; // Decrease the call count

        return callOnTheLine;
    }

    /**
     * The call center has a max capacity, at which point it
     * cannot take new calls until it answers and hangs up on
     * another call. This returns true if we are at capacity.
     * @return true if call center is full
     */
    public boolean isCallCenterAtCapacity()
    {
        return numCalls == callQueue.length;
    }

    /**
     * Returns the number of calls waiting to be answered.
     * @return number of calls in queue
     */
    public int numCallsWaiting()
    {
        return numCalls;
    }

    /**
     * Given a caller name, this returns the position in 
     * the queue for a caller. If there is no call with 
     * that name, then it returns -1. Worth noting that 
     * the call at the front of the queue is at position
     * 1 (but internally it is at index 0)
     * @param n name of the caller to search for
     * @return position (1..n) or -1 if not found
     */
    public int positionInLine(String n)
    {
        for (int i = 0; i < numCalls; i++) {
            if (callQueue[i].getName().equals(n)) {
                return i + 1; // Return 1-based position if found 
            }
        }
        return -1; // Return -1 if the name is not in the queue 
    }

    /**
     * Is the call center busy, i.e., are they on a call
     * right now?
     * @return true if call center is on a call
     */
    public boolean isBusy()
    {
        return callOnTheLine != null;
    }

    /**
     * Returns the call object that is current on the call 
     * with the call center. If there is no current call, 
     * returns null.
     * @return call object for the current call.
     */
    public Call onTheLine()
    {
        return callOnTheLine;
    }

    /**
     * Simulates time passing. The clock ticks and decrements
     * the time left on the call for a call that is online.
     */
    public void clockTick()
    {
        if (callOnTheLine != null) {
            callOnTheLine.decrement();
            if (callOnTheLine.getDuration() == 0) {
                callOnTheLine = null; // End call if duration is 0 
            }
        }
        clock++; // Advance simulation clock 
    }

    /**
     * Returns the current time in the simulation.
     * @return value of the clock
     */
    public int getClock()
    {
        return clock;
    }
}
