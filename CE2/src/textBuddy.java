import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class textBuddy {
	private static ArrayList<String> list = new ArrayList<String>();
	private static Scanner sc;
	private static BufferedWriter bw;
	private static String fileName;
	static File file_object = null;

	public static void main(String args[]) throws IOException {
		String command = new String("thisIsAStub");
		fileName = args[0];
		file_object = new File(fileName);
		file_object.createNewFile();
		if (file_object.exists()) {
			displayIntro(fileName);
			while (!command.equals("exit")) {
				command = askForCommand();
				processCommand(command);
			}
		}
		else{
			System.out.println("Sorry, unable to create the file");
		}
	}

	private static void processCommand(String command) throws IOException {
		// TODO Auto-generated method stub
		switch (command) {
		case "add":
			addToFile();
			break;
		case "delete":
			deleteFileContent();
			break;
		case "display":
			displayFileContent();
			break;
		case "clear":
			clearFileContent();
			break;
		case "exit":
			break;
		default:
			System.out.println("Invalid Command!");
			break;
		}

	}

	private static void clearFileContent() throws IOException {
		// TODO Auto-generated method stub
		list.removeAll(list);
		clearFile(fileName);
		System.out.println("all content deleted from " + fileName + "");
	}

	private static void displayFileContent() throws IOException {
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			System.out.println(fileName + " is empty");
		} else {
			printFile(fileName);
		}
	}

	private static void printFile(String filename2) throws IOException {
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

	private static void deleteFileContent() throws IOException {
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

	//This method will append and display the user input to the file
	//Pre-condition: will only accept String input
	private static void addToFile() throws IOException {
		// TODO Auto-generated method stub
		String data = new String();
		data = sc.nextLine();
		list.add(data);
		System.out.println("added to " + fileName + ": \""
				+ data.substring(1, data.length()) + "\"");
		// first we clear the content of the file
		clearFile(fileName);
		// then we append the new content to the file
		appendFile(list);
	}
	
	//This method will append the user input to the file
	private static void appendFile(ArrayList<String> list2)
			throws IOException {
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
			//close the writer so that it can write to the file
            bw.close(); 
        }

	}

	//This method will delete all content(s) of the file
	private static void clearFile(String filename2) throws IOException {
		// TODO Auto-generated method stub
		try {
			File file = new File(filename2);
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("");
			bw.close();
		} catch (IOException ee) {
			ee.printStackTrace();
		}

	}

	//This method prompts the user for an input
	//Pre-condition: only accepts String input
	private static String askForCommand() {
		System.out.print("Command: ");
		sc = new Scanner(System.in);
		return sc.next();
	}

	//This method shows the welcome message to the users, indicating the file is ready to use
	private static void displayIntro(String fileName) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to TextBuddy. " + fileName
				+ " is ready for use");
	}

}
