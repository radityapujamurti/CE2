import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TextBuddyTest {
	@Test 
	public void testProcessCommand() throws IOException{
		testOneCommand("simple display before any add","null is empty","display");
		testTwoCommand("clear file before any input","null is cleared!", "CE2.txt");
	}

	private void testOneCommand(String description, String expected, String command) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.processCommand(command));
	}
	
	private void testTwoCommand(String description, String expected, String fileName) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.clearFile(fileName));
	}
}
