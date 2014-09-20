import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class textBuddy {
	private static List<String> list = new ArrayList<String>();
	private static Scanner sc;
	private static BufferedWriter bw;
	public static String fileName;
	static File file_object = null;

	// format for String output
	public static final String ADD_NOTICE_FORMAT = "added to %s: \"%s\"";
	public static final String DELETE_NOTICE_FORMAT = "deleted from %s: \"%s\"";
	public static final String CLEAR_NOTICE_FORMAT = "all content deleted from %s";
	public static final String SEARCH_NOTICE_FORMAT = "results found: ";
	public static final String SORT_NOTICE_FORMAT = "the content of %s is sorted alphabetically.";
	public static final String REQUEST_COMMAND = "Command: ";
	public static final String ERROR_CREATING_FILE_MSG = "Sorry, unable to create %s";
	public static final String INVALID_COMMAND_WARNING = "Invalid Command!";
	public static final String EMPTY_FILE_WARNING = "%s is empty!";
	public static final String WELCOME_MESSAGE = "Welcome to TextBuddy. %s is ready for use";

	public static void main(String args[]) throws IOException {
		String command = new String("thisIsAStub");
		fileName = args[0];
		// System.out.println(WELCOME_MESSAGE);
		file_object = new File(fileName);
		file_object.createNewFile();
		if (file_object.exists()) {
			displayIntro();
			while (!command.equals("exit")) {
				command = askForCommand();
				processCommand(command);
			}
		} else {
			System.out
					.println(String.format(ERROR_CREATING_FILE_MSG, fileName));
		}
	}

	// this method is the "center" of the application where the command is
	// processed
	public static String processCommand(String input) throws IOException {
		String result = null;
		String inputArr[] = input.split(" ", 2);
		switch (inputArr[0]) {
		case "add":
			result = addToFile(inputArr[1]);
			return result;
		case "delete":
			deleteFileContent(inputArr[1]);
			break;
		case "display":
			result = displayFileContent();
			return result;
		case "clear":
			clearFileContent();
			break;
		case "sort":
			result = sortFile();
			break;
		case "search":
			result = searchFile(inputArr[1]);
		case "exit":
			break;
		default:
			result = INVALID_COMMAND_WARNING;
			System.out.println(result);
			return result;
		}
		return result;

	}

	// This method will search all strings that contain they keywords and
	// display them
	public static String searchFile(String keywords) {

		if (fileName.isEmpty()) {
			System.out.println(String.format(EMPTY_FILE_WARNING, fileName));
			return null;
		}

		boolean found = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(keywords)) {
				if (found == false)
					System.out.println(SEARCH_NOTICE_FORMAT);
				System.out.println(i + 1 + ". " + list.get(i));
				found = true;
			}
		}
		if (found == false) {
			System.out.println("Not found!");
			return "Not Found";
		}
		return "Found";

	}

	// This method will sort the content of the file alphabetically
	public static String sortFile() throws IOException {

		if (fileName.isEmpty()) {
			System.out.println(String.format(EMPTY_FILE_WARNING, fileName));
			return null;
		}

		String result = null;
		Collections.sort(list, new Comparator<String>() {
			public int compare(String string1, String string2) {
				return string1.compareTo(string2);
			}
		});

		result = clearFile(fileName);

		appendFile(list);

		System.out.println(String.format(SORT_NOTICE_FORMAT, fileName));
		return result;
	}

	public static void clearFileContent() throws IOException {
		list.removeAll(list);
		clearFile(fileName);
		System.out.println(String.format(CLEAR_NOTICE_FORMAT, fileName));
	}

	public static String displayFileContent() throws IOException {
		String result=null;
		if (list.isEmpty()) {
			System.out.println(String.format(EMPTY_FILE_WARNING, fileName));
			result = String.format(EMPTY_FILE_WARNING, fileName);
		} else {
			printFile(fileName);
		}
		return result;
	}

	/*This method will read the file line by line
	 * and display the content
	 */
	public static void printFile(String filename) throws IOException {
		BufferedReader br = null;
		try {
			String currLine;
			br = new BufferedReader(new FileReader(fileName));
			while ((currLine = br.readLine()) != null) {
				System.out.println(currLine);
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}
	}
	
	/*This method accepts the position of the string to be deleted 
	 * and display the result accordingly
	 * Pre-condition: the position is integer*/
	public static void deleteFileContent(String toBeDeleted) throws IOException {
		if (!list.isEmpty()) {
			int index = Integer.valueOf(toBeDeleted) - 1;
			String toBeDeletedString = list.remove(index);
			System.out.println(String.format(DELETE_NOTICE_FORMAT, fileName,
					toBeDeletedString));
			clearFile(fileName);
			appendFile(list);
		} else {
			System.out.println(String.format(EMPTY_FILE_WARNING, fileName));
		}
	}

	/*This method will append and display the user input to the file*/
	public static String addToFile(String toBeAdded) throws IOException {
		list.add(toBeAdded);
		String result = String.format(ADD_NOTICE_FORMAT, fileName, toBeAdded);
		System.out.println(result);
		// first we clear the content of the file
		clearFile(fileName);
		// then we append the new content to the file
		appendFile(list);
		return result;
	}

	/*This method will append the user input to the file
	 * with specified numbered format
	 */
	public static void appendFile(List<String> list) throws IOException {
		try {
			bw = new BufferedWriter(new FileWriter(file_object, true));
			for (int i = 0; i < list.size(); i++) {
				String taskToBeAppended = new String();
				taskToBeAppended = (i + 1) + "." + list.get(i);
				bw.write(taskToBeAppended + "\n");
			}

		} catch (IOException ee) {
			ee.printStackTrace();
		} finally {
			// close the writer so that it can write to the file
			bw.close();
		}

	}

	// This method will delete all content(s) of the file
	public static String clearFile(String filename) throws IOException {
		String result = null;
		try {
			File file = new File(filename);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
			result = fileName + " is cleared!";

		} catch (IOException ee) {
			ee.printStackTrace();
		}
		return result;

	}

	// This method prompts the user for an input
	// Pre-condition: only accepts String input
	public static String askForCommand() {
		System.out.print(REQUEST_COMMAND);
		sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// This method shows the welcome message to the users, indicating the file
	// is ready to use
	public static void displayIntro() {
		System.out.println(String.format(WELCOME_MESSAGE, fileName));
	}

}
