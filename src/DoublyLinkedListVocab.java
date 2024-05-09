import java.util.ArrayList;
import java.util.Collections;

public class DoublyLinkedListVocab {

	 Node head;
	private Node tail;
	private int counter; // counter for elements of the list

	public DoublyLinkedListVocab() {
		this.head = null;
		this.tail = null;
		this.counter = 0;
	}

	public void addAtHead(Vocab vocab) {
		Node temp = head;
		head = new Node(vocab, null, head);

		if (tail == null) {
			tail = head;
		} else {
			temp.prev = head;
		}

		counter++;
	}

	public void addBefore(Vocab referenceTopic, Vocab newTopic) {
		Node position = head;
		while (position != null && !position.vocab.equals(referenceTopic)) {
			position = position.next;
		}
		if (position != null && position.vocab.equals(referenceTopic)) {
			if (position == head) {
				addAtHead(newTopic);
				return;
			}
			Node n = new Node(newTopic, null, null);
			n.next = position;
			n.prev = position.prev;
			position.prev = n;
			n.prev.next = n;
			counter++;
		}
	}

	public void addAtTail(Vocab vocab) {
		Node temp = tail;
		tail = new Node(vocab, tail, null);
		if (head == null) {
			head = tail;
		} else {
			temp.next = tail;
		}

		counter++;
	}

	public void addAfter(Vocab referenceTopic, Vocab newTopic) {
		Node position = head;
		while (position != null && !position.vocab.equals(referenceTopic)) {
			position = position.next;
		}
		if (position != null && position.vocab.equals(referenceTopic)) {
			if (position.next == null) {
				addAtTail(newTopic);
				return;
			}
			Node n = new Node(newTopic, null, null);
			n.next = position.next;
			n.prev = position;
			n.next.prev = n;
			position.next = n;
			counter++;
		}
	}

	public void removeHead() {
		if (head == null)
			return; // If the list is empty, do nothing.

		if (head == tail) { // If there's only one element in the list
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		counter--; // Decrement the size of the list
	}

	public void removeTail() {
		if (tail == null)
			return; // If the list is empty, do nothing.

		if (head == tail) { // If there's only one element in the list
			head = tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		counter--; // Decrement the size of the list
	}

	public void remove(Vocab vocab) {
		if (head == null)
			return; // If the list is empty, do nothing.

		if (head.vocab.equals(vocab)) {
			removeHead(); // Special case for head
			return;
		}

		if (tail.vocab.equals(vocab)) {
			removeTail(); // Special case for tail
			return;
		}

		// General case: search for the node
		Node current = head;
		while (current != null && !current.vocab.equals(vocab)) {
			current = current.next;
		}

		if (current != null) { // Node found in the middle of the list
			current.prev.next = current.next;
			current.next.prev = current.prev;
			counter--; // Adjust the counter
		}
	}

	public int size() {
		return counter;
	}

	public Vocab findTopicByName(String topicName) {
		Node current = head; // Start traversing from the head of the list
		while (current != null) { // Continue until the end of the list
			if (current.vocab.getTopicName().equalsIgnoreCase(topicName)) { // Case insensitive comparison
				return current.vocab; // Return the Vocab object if found
			}
			current = current.next; // Move to the next node
		}
		return null; // Return null if no matching topic is found
	}

	public void displayAllTopics() {

		int numtopics = 1;
		if (head == null) {
			System.out.println("The list is empty.");
			return;
		}
		
		

		Node current = head;

		while (current != null) {
			// Display each topic's name
			System.out.println(numtopics + " " + current.vocab.getTopicName());

			numtopics++;
			// Move to the next node
			current = current.next;
		}
	}
	

	public Vocab getTopicByIndex(int index) {
		if (index <= 0) {
			System.out.println("Invalid topic number.");
			return null;
		}

		int currentIndex = 1; // Start from the first element
		Node current = head; // Start traversal from the head of the list
		while (current != null && currentIndex < index) {
			current = current.next; // Move to the next node
			currentIndex++; // Increment the position counter
		}
		
		if (current != null) {
			return current.vocab; // Found the node at the index, return its Vocab object
		}
		return null; // In case of no match found, return null
	}

	public void searchForWord(String word) {
        boolean found = false;
        Node current = head;
        System.out.println("Searching for the word '" + word + "' in all topics:");
        while (current != null) {
            if (current.vocab.getWords().contains(word)) {
                System.out.println("- Found in topic: " + current.vocab.getTopicName());
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("The word '" + word + "' was not found in any topic.");
        }
    }
	
	public void showWordsStartingWith(char letter) {
	    ArrayList<String> results = new ArrayList<>();
	    Node current = head;
	    char target = Character.toLowerCase(letter);  // handle case sensitivity

	    while (current != null) {
	        // Access the words linked list of the current Vocab node
	        SinglyLinkedListVocab.Node wordNode = current.vocab.getWords().head;

	        // Traverse the words linked list
	        while (wordNode != null) {
	            if (Character.toLowerCase(wordNode.value.charAt(0)) == target) {
	                results.add(wordNode.value);
	            }
	            wordNode = wordNode.next;
	        }

	        current = current.next;
	    }

	    // Sorting and displaying results
	    Collections.sort(results);
	    System.out.println("Words starting with '" + letter + "':");
	    for (String word : results) {
	        System.out.println(word);
	    }
	}

	// inner class
	private class Node {
		public Vocab vocab;
		private Node prev;
		private Node next;

		public Node(Vocab vocab, Node prev, Node next) {
			this.vocab = vocab;
			this.prev = prev;
			this.next = next;

		}

	}
}
