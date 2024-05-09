
public class SinglyLinkedListVocab {

	Node head;
	private Node tail;

	private int size;

	public SinglyLinkedListVocab() {
		head = null;
		size = 0;
	}

	public void addAtHead(String value) {
		head = new Node(value, head);
		size++;
	}

	public void insert(String value) {
		if (head == null) {
			addAtHead(value);
		} else {
			Node position = head;
			while (position.next != null) {
				position = position.next;
			}

			Node n = new Node(value, null);
			position.next = n;

			size++;
		}
	}

	public String removeHead() {
		if (head != null) {
			Node temp = head;
			head = head.next;
			size--;
			temp.next = null;
			return temp.value;
		}
		return null; // Or throw an Exception
	}

	public String removeValue(String value) {
		if (head == null) {
			return null; // list is empty
		}
		if (head.value.equals(value)) {
			return removeHead(); // special case for head
		}
		Node position = head;
		while (position.next != null && !position.next.value.equals(value)) {
			position = position.next; // traverse to find the node right before the one to remove
		}
		if (position.next != null && position.next.value.equals(value)) {
			Node temp = position.next;
			position.next = position.next.next; // remove the node
			size--;
			return temp.value; // return the removed value
		}
		return null; // if not found, return null
	}

	public Node find(String value) {
		Node node = head;

		while (node != null) {
			if (node.value.equals(value)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}

	public String getValueAtIndex(int index) {
		if (index <= 0) {
			System.out.println("Invalid topic number.");
			return null;
		}
		int currIndex = 1;
		Node currNode = head;
		while (currIndex < index && currNode != null) {
			currIndex++;
			currNode = currNode.next;
		}
		if (currNode == null) {
			return null;
		} else {
			return currNode.value;
		}
	}

	public void replace(String oldvalue, String newvalue) {
		Node node = find(oldvalue);
		if (node != null) {
			node.value = newvalue;
		} else {
			System.out.println("word not found");
		}
	}

	public int size() {
		return size;
	}

	public void display(int maxWordLength) {
		Node current = head;
		int wordCount = 1; // Start counting from 1

		if (current == null) {
			System.out.println("The topic is empty");
		}
		while (current != null) {
			String formatString = "%d: %-" + maxWordLength + "s\t";
			System.out.printf(formatString, wordCount, current.value);// Print each word with a number
			if (wordCount % 4 == 0) {
				System.out.println();
			}
			current = current.next;
			wordCount++; // Increment the word count
		}
	}

	public boolean contains(String targetWord) {
		Node current = head;
		while (current != null) {
			if (current.value.equalsIgnoreCase(targetWord)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}

	class Node {

		String value;
		Node next;

		public Node(String value, Node next) {
			this.value = value;
			this.next = null;
		}
	}
}
