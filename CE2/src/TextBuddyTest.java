import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

public class TextBuddyTest {
	@Test
	
	public void testProcessCommand() throws IOException{
		testOneCommand("unsuccesful search: search empty file", "CE2.txt is empty","search test");
		testOneCommand("unsuccesful sort: sorting empty file","CE2.txt is empty!","sort");
		testOneCommand("simple display before any add","CE2.txt is empty","display CE2.txt");
		testOneCommand("simple add to file","added to CE2.txt: \"test 1\"","add test 1");
		testOneCommand("simple add to file","added to CE2.txt: \"test 2\"","add test 2");
		testOneCommand("simple add to file","added to CE2.txt: \"random\"","add random");
		testOneCommand("successful search file","Found!","search test");
		testOneCommand("unsuccessful search file","Not found!","search simple");
		testOneCommand("successful sort","CE2.txt is is sorted alphabetically!","sort");
			
	}

	private void testOneCommand(String description, String expected, String command) throws IOException {
		// TODO Auto-generated method stub
		assertEquals(description, expected, textBuddy.processCommand(command));
	}
	
}
