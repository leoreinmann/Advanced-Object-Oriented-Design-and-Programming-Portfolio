import java.security.InvalidParameterException;
import java.util.ArrayList;


/**
 * Trie uses the class node to build the <a href="https://en.wikipedia.org/wiki/Trie#">trie data structure</a>
 */
public class Trie {

    private final Node root;

    public Trie() {
        root = new Node();
    }


    /**
     * Inserts a word into the trie data structure.
     *
     * @param word lowercase word without special characters
     */
    public void insertWord(String word) throws InvalidParameterException {
        // Validate input word
        if (!validateWord(word)) {
            throw new InvalidParameterException();
        }

        Node currentNode = root;
        StringBuilder value = new StringBuilder();
        for (char character : word.toCharArray()) {
            // If not present at index in children array, then create new node
            if (currentNode.getChildren()[character - 'a'] == null) {
                currentNode.getChildren()[character - 'a'] = new Node();
                currentNode.setValue(value.toString());
            }
            // Set value and hop to next node
            value.append(character);
            currentNode = currentNode.getChildren()[character - 'a'];
        }
        // Set value of node and mark node as terminal
        currentNode.setTerminal(true);
        currentNode.setValue(value.toString());
    }


    /**
     * Validates input for insertWord(String word). Only words with letters [a-z] are valid. Uppercase characters will be replaced
     * lowercase with lowercase characters
     *
     * @param word Input word of insertWord(String word)
     * @return true if word is valid
     */
    private boolean validateWord(String word) {
        // Lowercase word
        word = word.toLowerCase();

        // Check if all characters are valid [a-z]
        for (char character : word.toCharArray()) {
            if (character < 'a' || character > 'z') {
                return false;
            }
        }
        return true;
    }


    /**
     * Searches for given word in the trie data structure and returns true in case of success.
     *
     * @param word lowercase word without special characters
     * @return true in case of success
     */
    public boolean findWord(String word) {
        Node currentNode = root;
        for (char character : word.toCharArray()) {
            // If character at index in children array is null, then return false
            currentNode = currentNode.getChildren()[character - 'a'];
            if (currentNode == null) {
                return false;
            }
        }
        return currentNode.isTerminal();
    }


    /**
     * Helper method findWordSubsequence(String subsequence). Searches for subsequences in the trie.
     *
     * @param node        current node where the algorithm will search for
     * @param subsequence value the algorithm searches for
     * @param currentWord current word in the trie.
     * @param lookup      memoization table to keep track which word the algorithm already found
     */
    private void findWordSubsequence(Node node, String subsequence, String currentWord, ArrayList<String> lookup) {
        if (subsequence.isEmpty()) {
            // If the subsequence is empty, print the current word and word is not in lookup table
            if (node.isTerminal() && !lookup.contains(currentWord)) {
                System.out.println(currentWord);
                lookup.add(currentWord);
            }
        }

        Node[] children = node.getChildren();

        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                char ch = (char) ('a' + i);

                // Check if the current child node's value matches the next character in the subsequence
                if (subsequence.startsWith(String.valueOf(ch))) {
                    // Recursively search for words with the remaining subsequence
                    findWordSubsequence(children[i], subsequence.substring(1), currentWord + ch, lookup);
                }

                // Continue searching for words without consuming characters from the subsequence
                findWordSubsequence(children[i], subsequence, currentWord + ch, lookup);
            }
        }
    }


    /**
     * Wrapper method to find a subsequence in the trie.
     *
     * @param subsequence String word the algorithm searches for
     */
    public void findWordSubsequence(String subsequence) {
        ArrayList<String> lookup = new ArrayList<>();
        findWordSubsequence(root, subsequence, "", lookup);
    }


    /**
     * Helper method for printAllWords().
     *
     * @param node   Current node in the algorithm
     * @param prefix concatenated string used while traversing the trie
     */
    public void printAllWords(Node node, String prefix) {
        if (node.isTerminal()) {
            System.out.println(prefix);
        }

        Node[] children = node.getChildren();
        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                char character = (char) ('a' + i);
                printAllWords(children[i], prefix + character);
            }
        }
    }

    /**
     * Wrapper method to start printing from the root
     */
    public void printAllWords() {
        printAllWords(root, "");
    }


    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] words = new String[]{"word", "work", "hardware", "warden", "shop", "computer", "compare"};

        for (String word : words) {
            trie.insertWord(word);
        }

        trie.printAllWords();

        trie.findWordSubsequence("com");

        System.out.println(trie.findWord("work"));
        System.out.println(trie.findWord("container"));
    }

}
