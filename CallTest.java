import itsc2214.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 * Unit tests for the Call class.
 * Verifies correct behavior of constructors, accessors, mutators, and string output.
 * Designed to ensure all branches and conditions are covered.
 * 
@author  Jay Anupoju
@version Jul 29, 2025
*/
public class CallTest {
    
    /**
     * Tests the default constructor to ensure default values are set correctly.
     */
    @Test
    public void testDefaultConstructor()
    {
        Call call = new Call();
        assertEquals("Unknown Caller", call.getName());
        assertFalse(call.isVIP());
        assertEquals(10, call.getDuration());
    }

    /**
     * Tests the parameterized constructor with valid inputs.
     */
    @Test
    public void testParameterizedConstructorValid()
    {
        Call c = new Call("Alice", true, 5);
        assertEquals("Alice", c.getName());
        assertTrue(c.isVIP());
        assertEquals(5, c.getDuration());
    }

    /**
     * Tests constructor with empty name; defaults to "Unknown Caller".
     */
    @Test
    public void testParameterizedConstructorWithEmptyName()
    {
        Call call = new Call("", false, 8);
        assertEquals("Unknown Caller", call.getName());
        assertFalse(call.isVIP());
        assertEquals(8, call.getDuration());
    }

    /**
     * Tests constructor with null name; defaults to "Unknown Caller".
     */
    @Test
    public void testParameterizedConstructorWithNullName()
    {
        Call call = new Call(null, true, 12);
        assertEquals("Unknown Caller", call.getName());
        assertTrue(call.isVIP());
        assertEquals(12, call.getDuration());
    }

    /**
     * Tests constructor with invalid duration; defaults to 1.
     */
    @Test
    public void testParameterizedConstructorInvalidDuration()
    {
        Call call = new Call("Alice", false, 0);
        assertEquals("Alice", call.getName());
        assertFalse(call.isVIP());
        assertEquals(1, call.getDuration());
    }

    /**
     * Tests that getName() returns the correct caller name.
     */
    @Test
    public void testGetName()
    {
        Call call = new Call("Michael", false, 10);
        assertEquals("Michael", call.getName());
    }

    /**
     * Tests that isVIP() returns true for a VIP caller.
     */
    @Test
    public void testIsVIP()
    {
        Call call = new Call("Sarah", true, 5);
        assertTrue(call.isVIP());
    }

    /**
     * Tests that getDuration() returns the correct duration.
     */
    @Test
    public void testGetDuration()
    {
        Call call = new Call("David", false, 7);
        assertEquals(7, call.getDuration());
    }

    /**
     * Tests that decrement() reduces duration by 1.
     */
    @Test
    public void methodName()
    {
        Call call = new Call("Bob", false, 5);
        call.decrement();
        assertEquals(4, call.getDuration());
    }
}
