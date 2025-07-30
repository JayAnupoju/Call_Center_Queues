import itsc2214.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
Unit tests for the CallCenter class.
Validates behavior of call queue management, VIP handling, time progression,
and answering calls through the simulated call center abstraction.

@author Jay Anupoju
@version Jul 29, 2025
*/
public class CallCenterTest {

    /**
     * Tests initial state of CallCenter after default construction.
     */
    @Test
    public void testConstructor()
    {
        CallCenter callCenter = new CallCenter();

        assertEquals(0, callCenter.getClock());
        assertFalse(callCenter.isBusy());
        assertFalse(callCenter.isCallCenterAtCapacity());
        assertNull(callCenter.onTheLine());
        assertEquals(0, callCenter.numCallsWaiting());
    }
    
    /**
     * Tests adding regular and VIP calls and verifies their position in line.
     */
    @Test
    public void testAddCalls()
    {
        CallCenter callCenter = new CallCenter();

        callCenter.addCall("Alice", false, 5);  // Regular call
        callCenter.addCall("Bob", false, 10);   // Regular call
        callCenter.addCall("Charlie", true, 3); // VIP call

        assertEquals(3, callCenter.numCallsWaiting());

        assertEquals(1, callCenter.positionInLine("Charlie")); // VIP should be first
        assertEquals(2, callCenter.positionInLine("Alice"));
        assertEquals(3, callCenter.positionInLine("Bob"));
    }

    /**
     * Tests adding calls until queue reaches capacity and rejects additional calls.
     */
    @Test
    public void testQueueAtCapacity()
    {
        CallCenter callCenter = new CallCenter();
        for (int i = 0; i < 10; i++) {
            callCenter.addCall("Caller " + i, false, 5);
        }
        assertTrue(callCenter.isCallCenterAtCapacity());                    // Queue should be full
        assertFalse(callCenter.addCall("Extra Caller", false, 5));    // Extra call should rejected
        assertEquals(10, callCenter.numCallsWaiting());            // Queue size remains 10
    }

    /**
     * Tests that VIP calls are added to the front of the queue.
     */
    @Test
    public void testAddVIPCall()
    {
        CallCenter callCenter = new CallCenter();
        callCenter.addCall("Alice", false, 5);
        callCenter.addCall("Bob", false, 5);
        callCenter.addCall("Charlie", true, 3);
        assertEquals(1, callCenter.positionInLine("Charlie"));  // VIP should be first
        assertEquals(2, callCenter.positionInLine("Alice"));
        assertEquals(3, callCenter.positionInLine("Bob"));
    }

    /**
     * Tests that clockTick() advances time and decrements call duration,
     * ending the call when duration reaches 0.
     */
    @Test
    public void testClockTick()
    {
        CallCenter callCenter = new CallCenter();
        callCenter.addCall("John", false, 5);
        callCenter.answerCall();
        assertEquals(0, callCenter.getClock());
        callCenter.clockTick();

        assertEquals(4, callCenter.onTheLine().getDuration());
        assertEquals(1, callCenter.getClock());
        callCenter.clockTick();

        assertEquals(3, callCenter.onTheLine().getDuration());
        assertEquals(2, callCenter.getClock());

        for (int i = 0; i < 3; i++) {
            callCenter.clockTick();
        }

        assertNull(callCenter.onTheLine());
        assertEquals(5, callCenter.getClock()); 
    }

    /**
     * Tests that answerCall() returns the next call and won't answer if already busy.
     */
    @Test
    public void testAnswerCall()
    {
        CallCenter callCenter = new CallCenter();

        // No calls to answer initially
        assertNull(callCenter.answerCall());

        // Add two calls
        callCenter.addCall("Alice", false, 5);
        callCenter.addCall("Bob", false, 5);

        // Answer first call
        Call answeredCall = callCenter.answerCall();
        assertNotNull(answeredCall);
        assertEquals("Alice", answeredCall.getName()); 
        assertEquals(1, callCenter.numCallsWaiting()); 

        // Add another call while one is active
        callCenter.addCall("Charlie", false, 5);

        // Should not answer a new call while one is on the line
        assertNull(callCenter.answerCall()); 
    }
}
