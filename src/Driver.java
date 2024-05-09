



import java.io.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.ArrayList;

public class Driver extends Exception {
	static DoublyLinkedListVocab myLinkedList = new DoublyLinkedListVocab();
	static ArrayList<String> words = new ArrayList<>();

	public static void main(String[] args) throws IOException, FileNotFoundException {

		Scanner sc = new Scanner(System.in);

		while (true) {
			displayMenu();
			System.out.println("Enter your choice (integers):");
			try {
				int choice = sc.nextInt();
				sc.nextLine(); // Consume the newline left-over

				switch (choice) {
				case 1:

					menuChoice();
					int TopicChoice = sc.nextInt();
					sc.nextLine();
					if (TopicChoice == 0) {
						break;
					}
					Vocab selectedTopic = myLinkedList.getTopicByIndex(TopicChoice);
					selectedTopic.displayWords();

					break;

				case 2:
					menuChoice12();
					int TopicChoice2 = sc.nextInt();
					sc.nextLine();

					if (TopicChoice2 == 0) {
						break;
					}
					Vocab VrefTopic = myLinkedList.getTopicByIndex(TopicChoice2);
					System.out.println("What is the topic you want to add?");
					String newTopic = sc.nextLine();
					Vocab VnewTopic = new Vocab(newTopic);
					myLinkedList.addBefore(VrefTopic, VnewTopic);

					System.out.println("Enter a word - to quit press enter");
					while (true) {
						String line = sc.nextLine(); // Read the whole line of input
						if (line.isEmpty()) {
							// If the user just presses Enter (input is empty), exit the loop
							break;
						}
						// Split the line into words based on spaces
						String[] words = line.split("\\s+"); // "\\s+" regex matches one or more whitespace characters
						for (String word : words) {
							VnewTopic.addWord(word); // Adds each word individually, trimming any extra spaces

						}
						break;
					}
					break;

				case 3:
					menuChoice12();
					int TopicChoice3 = sc.nextInt();
					sc.nextLine();
					if (TopicChoice3 == 0) {
						break;
					}
					Vocab VrefTopic2 = myLinkedList.getTopicByIndex(TopicChoice3);
					System.out.println("What is the topic you want to add?");
					String newTopic2 = sc.nextLine();
					Vocab VnewTopic2 = new Vocab(newTopic2);
					myLinkedList.addAfter(VrefTopic2, VnewTopic2);
					System.out.println("Enter a word - to quit press enter");
					while (true) {
						String line = sc.nextLine(); // Read the whole line of input
						if (line.isEmpty()) {
							// If the user just presses Enter (input is empty), exit the loop
							break;
						}
						// Split the line into words based on spaces
						String[] words = line.split("\\s+"); // "\\s+" regex matches one or more whitespace characters
						for (String word : words) {
							VnewTopic2.addWord(word); // Adds each word individually, trimming any extra spaces
						}
						break;
					}
					break;
				case 4:
					menuChoice();
					int TopicChoice4 = sc.nextInt();
					Vocab VrefTopic3 = myLinkedList.getTopicByIndex(TopicChoice4);
					myLinkedList.remove(VrefTopic3);
					break;
				case 5:
					modifyVocabulary();
					break;
				case 6:
					System.out.println("Enter the word to search for:");
					String searchWord = sc.nextLine();
					myLinkedList.searchForWord(searchWord);
					break;

				case 7:
					System.out.println("Enter the file path which you want to load:");
					String filename = sc.nextLine();
					myLinkedList = readFile(filename);
					break;
				case 8:
				
				    System.out.println("Enter a letter:");
				    String input = sc.nextLine();
				    if (input.length() > 0) {
				        char letter = input.charAt(0);
				        myLinkedList.showWordsStartingWith(letter);
				    } else {
				        System.out.println("No letter entered.");
				    }
				    break;
				
				case 9:
					System.out.println("What is the file name you want to save to");
					String file=sc.nextLine();
					SaveFile(file);
					break;
				case 0:
					System.out.println("Exiting program.");
					sc.close();
					System.exit(0);
				}

			} catch (InputMismatchException e) {
				System.out.println("Error: please enter a valid integer option.");
				sc.next(); // Consume the invalid input
			}
		}
	}

	public static void displayMenu() {
		System.out.println("-------------------------------");
		System.out.println("  Vocabulary Control Center");
		System.out.println("-------------------------------");
		System.out.println(" 1  browse a topic ");
		System.out.println(" 2  insert a new topic before another one");
		System.out.println(" 3  insert a new topic after another one");
		System.out.println(" 4  remove a topic");
		System.out.println(" 5  modify a topic");
		System.out.println(" 6  search topics for a word");
		System.out.println(" 7  load from a file");
		System.out.println(" 8  show all words starting with a given letter");
		System.out.println(" 9  save to file");
		System.out.println(" 0  exit");
		System.out.println("-----------------------------------------------------------");
	}

	public static DoublyLinkedListVocab readFile(String filename) {

		SinglyLinkedListVocab wordList = new SinglyLinkedListVocab();
		DoublyLinkedListVocab store = new DoublyLinkedListVocab();
		try (BufferedReader reader = new BufferedReader(new FileReader("./src/" + filename))) {
			String line;
			Vocab currentVocab = null;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("#")) {
					String topicname = line.substring(1).trim(); // Remove '#' and trim spaces
					currentVocab = new Vocab(topicname);
					store.addAtHead(currentVocab);
				}

				else if (!line.isEmpty() && currentVocab != null) { // Word line (ignoring empty lines)
					currentVocab.addWord(line); // Add word to the linked list when there are empty spaces
				}

			}

			reader.close();

		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		return store;
	}

	public static void SaveFile(String filename) throws IOException {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(filename));

			for (int i = 1; i <= myLinkedList.size(); i++) {
				Vocab currentVocab = myLinkedList.getTopicByIndex(i);

				// collects the words with the hashtag prefix
				writer.println("#" + currentVocab.getTopicName());

				for (int j = 1; j <= currentVocab.getWords().size(); j++) {
					String word = currentVocab.getWords().getValueAtIndex(j);
					System.out.println(word);

					writer.println(word);
				}
			}

			for (int i = 1; i <= myLinkedList.size(); i++) {
				Vocab currentVocab = myLinkedList.getTopicByIndex(i);

				// collects the words with the hashtag prefix
				System.out.println("#" + currentVocab.getTopicName());

				for (int j = 1; j <= currentVocab.getWords().size(); j++) {
					String word = currentVocab.getWords().getValueAtIndex(j);
					System.out.println(word);

					writer.println(word);
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (writer != null) {

				writer.close();

			}
		}
	}

	public static void menuChoice() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("        pick a topic           ");
		System.out.println("-------------------------------");
		myLinkedList.displayAllTopics();
		System.out.println("0 Exit");
		System.out.println("Enter your choice ");

	}
	public static void menuChoice12() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("        pick a topic           ");
		System.out.println("-------------------------------");
		if (myLinkedList.head==null) {
			
		
		System.out.println("What is the name of the topic you want to add?");
        String newTopic = sc.nextLine();
        Vocab newVocab = new Vocab(newTopic);
        myLinkedList.addAtHead(newVocab);  // Directly adding the new topic at head
        System.out.println("New topic added. You can now add words to this topic in the \"modify a topic menu\".");
        System.out.println("0 Exit");
		} else {
		myLinkedList.displayAllTopics();
		System.out.println("0 Exit");
		System.out.println("Enter your choice ");
		}
	}

	public static void modifyVocabulary() {
		menuChoice();
		Scanner scanner = new Scanner(System.in);

		// Display topics and ask the user to choose one
		
		int choice=scanner.nextInt();
		scanner.nextLine();

		Vocab vocab = myLinkedList.getTopicByIndex(choice);

		if (vocab == null) {
			System.out.println("Topic not found.");
			return;
		}

		// If topic is found, show options for modifying the vocabulary
		System.out.println("Select modification action:");
		System.out.println("a Add a word to the topic");
		System.out.println("r Remove a word from the topic");
		System.out.println("c Replace a word in the topic");
		String action = scanner.nextLine();
		
		switch (action) {
		case "a":
			System.out.println("Enter a word to add:");
			String wordToAdd = scanner.nextLine();
			vocab.addWord(wordToAdd);
			break;
		case "r":
			System.out.println("Enter a word to remove:");
			String wordToRemove = scanner.nextLine();
			vocab.removeWord(wordToRemove);
			break;
		case "c":
			System.out.println("Enter the word you want to replace:");
			String oldWord = scanner.nextLine();
			System.out.println("Enter the new word:");
			String newWord = scanner.nextLine();
			vocab.changeWord(oldWord, newWord);
			break;
		default:
			System.out.println("Invalid action selected.");
			break;
		}
	}
}
