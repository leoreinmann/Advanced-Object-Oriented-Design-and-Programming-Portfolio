public class Node {

    private Node[] children;

    private boolean isTerminal;

    private String value;

    // region getter and setter
    public Node[] getChildren() {
        return children;
    }

    public void setChildren(Node[] children) {
        this.children = children;
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
