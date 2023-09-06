/**
 * Node for Trie data structure. The node can only store lowercase words with letters of latin alphabet [a-z]
 */
public class Node {

    private final Node[] children;

    private boolean isTerminal;

    private String value;


    /**
     * Initialize children array and set all variables to null/false
     */
    public Node() {
        // Node has at max 26 children because of 26 letters in latin alphabet
        final int ALPHABET_SIZE = 26;
        children = new Node[ALPHABET_SIZE];

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            children[i] = null;
        }

        isTerminal = false;

        value = null;
    }

    // region getter and setter
    public Node[] getChildren() {
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
