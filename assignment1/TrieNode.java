/**
 * Node for Trie data structure. The node can only store lowercase words with letters of latin alphabet [a-z]
 */
public class TrieNode {

    private final TrieNode[] children;

    private boolean isTerminal;

    private String value;


    /**
     * Initialize children array and set all variables to null/false
     */
    public TrieNode() {
        // Node has at max 26 children because of 26 letters in latin alphabet
        final int ALPHABET_SIZE = 26;
        children = new TrieNode[ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            children[i] = null;
        }

        isTerminal = false;

        value = null;
    }

    // region getter and setter
    public TrieNode[] getChildren() {
        return children;
    }


    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    // endregion


}
