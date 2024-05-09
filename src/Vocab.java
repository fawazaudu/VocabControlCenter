
public class Vocab {

	String TopicName;
	SinglyLinkedListVocab words;
	int maxWordLength;

	public Vocab(String topicName) {
		this.TopicName = topicName;
		this.words = new SinglyLinkedListVocab();
		maxWordLength = 0;
	}

	public String getTopicName() {
		return TopicName;
	}

	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	public SinglyLinkedListVocab getWords() {
		return words;
	}

	public void setWords(SinglyLinkedListVocab words) {
		this.words = words;
	}

	public void addWord(String word) {
		words.insert(word);
		if (word.length() > maxWordLength) {
			maxWordLength = word.length();
		}
	}

	public void removeWord(String word) {
		words.removeValue(word);
	}

	public void changeWord(String oldWord, String newWord) {
		if (words.find(oldWord) != null) {
			words.replace(oldWord, newWord);
		} else {
			System.out.println("Word not found");
		}
	}

	public void displayWords() {
		System.out.println("Topic: " + TopicName);
		words.display(maxWordLength); // Make sure SinglyLinkedListVocab has a display method that outputs words
		System.out.println();
	}
}
