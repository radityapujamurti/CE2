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
	private static String fileName;
	static File file_object = null;
	
	private static String ERROR_CREATING_FILE_MSG = "Sorry, unable to create the file";
	private static String INVALID_COMMAND_WARNING = "Invalid Command!";
	private static String EMPTY_FILE_WARNING = fileName + " is empty!";
	private static String WELCOME_MESSAGE = "Welcome to TextBuddy. " + fileName
			+ " is ready for use";
	
	public static void main(String args[]) throws IOException {
		String command = new String("thisIsAStub");
		fileName = args[0];
		initialize(fileName);
		if (file_object.exists()) {
			displayIntro(fileName);
			while (!command.equals("exit")) {
				command = askForCommand();
				processCommand(command);
			}
		} else {
			System.out.println(ERROR_CREATING_FILE_MSG);
		}
	}

	//This method will create the file according to the specified file name
	public static String initialize(String fileName) throws IOException {
		String result = null;
		file_object = new File(fileName);
		file_object.createNewFile();
		if (file_object.exists()) {
			result = "file created!";
		}
		return result;
	}

	public static String processCommand(String input) throws IOException {
		// TODO Auto-generated method stub
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

	//This method will search all strings that contain they keywords
	public static String searchFile(String keywords) {

		if (fileName.isEmpty()) {
			System.out.println(EMPTY_FILE_WARNING);
			return EMPTY_FILE_WARNING;
		}

		boolean found = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(keywords)) {
				if (found == false)
					System.out.println("Result found: ");
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

	public static String sortFile() throws IOException {
		String result = null;
		Collections.sort(list, new Comparator<String>() {
			public int compare(String string1, String string2) {
				return string1.compareTo(string2);
			}
		});

		result = clearFile(fileName);

		appendFile(list);

		System.out.println(fileName + " is sorted alphabetically!");
		return result;
	}

	public static void clearFileContent() throws IOException {
		// TODO Auto-generated method stub
		list.removeAll(list);
		clearFile(fileName);
		System.out.println("all content deleted from " + fileName + "");
	}

	public static String displayFileContent() throws IOException {
		// TODO Auto-generated method stub
		String result;
		if (list.isEmpty()) {
			result = EMPTY_FILE_WARNING;
			System.out.println(result);
		} else {
			printFile(fileName);
			result = fileName + "is not empty";
		}
		return result;
	}

	public static void printFile(String filename2) throws IOException {
		// TODO Auto-generated method stub
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

	public static void deleteFileContent(String tobeDeleted) throws IOException {
		// TODO Auto-generated method stub
		if (!list.isEmpty()) {
			String toBeDeleted = sc.next();
			sc.nextLine();
			int index = Integer.valueOf(toBeDeleted) - 1;
			String removed = list.remove(index);
			System.out.println("deleted from " + fileName + ": \""
					+ removed.substring(1, removed.length()) + "\"");
			clearFile(fileName);
			appendFile(list);
		} else {
			System.out.println("The file is empty. Nothing to delete.");
		}

	}

	// This method will append and display the user input to the file
	// Pre-condition: will only accept String input
	public static String addToFile(String toBeAdded) throws IOException {
		// TODO Auto-generated method stub
		list.add(toBeAdded);
		String result = "added to " + fileName + ": \"" + toBeAdded + "\"";
		System.out.println(result);
		// first we clear the content of the file
		clearFile(fileName);
		// then we append the new content to the file
		appendFile(list);
		return result;
	}

	// This method will append the user input to the file
	public static void appendFile(List<String> list2) throws IOException {
		// TODO Auto-generated method stub
		try {
			bw = new BufferedWriter(new FileWriter(file_object, true));
			for (int i = 0; i < list2.size(); i++) {
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
	public static String clearFile(String filename2) throws IOException {
		// TODO Auto-generated method stub
		String result = null;
		try {
			File file = new File(filename2);
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
		System.out.print("Command: ");
		sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// This method shows the welcome message to the users, indicating the file
	// is ready to use
	private static void displayIntro(String fileName) {
		// TODO Auto-generated method stub
		System.out.println(WELCOME_MESSAGE);
	}

}
