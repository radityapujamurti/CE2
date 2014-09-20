import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TextBuddyTest {
	@Test 
	
	public void testProcessCommand() throws IOException{
		
		testInitialize("initialize the file","CE2 created!","CE2.txt");
		//testOneCommand("simple display before any add","CE2.txt is empty","display CE2.txt");
		//testOneCommand("simple add to file","added to CE2.txt: test 1","add test1");
		
		//testThreeCommand("simple add","added to CE2.txt: \"test1\"", "test1");
		//testTwoCommand("clear file before any input","null is cleared!", "CE2.txt");
	}
	
	private void testInitialize(String description, String expected, String fileName) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.initialize(fileName));
	}

	private void testOneCommand(String description, String expected, String command) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.processCommand(command));
	}
	
	private void testTwoCommand(String description, String expected, String fileName) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.clearFile(fileName));
	}
	
	private void testThreeCommand(String description, String expected, String toBeAdded) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.addToFile(toBeAdded));
	}
}
