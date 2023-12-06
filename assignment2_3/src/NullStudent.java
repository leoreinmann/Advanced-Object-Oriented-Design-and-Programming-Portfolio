public class NullStudent extends Student {
    public NullStudent() {
        super(null, null, null, 0);
    }



    @Override
    public String toString() {
        return "NullStudent";
    }
}