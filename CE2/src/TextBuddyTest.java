import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;

import org.junit.Test;

public class TextBuddyTest {
	@Test 
	
	public void testProcessCommand() throws IOException{
		//String[] args = new String[1];
		//args[0]= "CE2.txt";
		//textBuddy.main(args);
		testInitialize("initialize the file","file created!","CE2.txt");
		testOneCommand("simple display before any add","CE2.txt is empty","display");
		//testOneCommand("simple add","added to CE2.txt: test 1","add test1");
		
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
