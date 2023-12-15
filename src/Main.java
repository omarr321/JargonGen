import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        SentenceBuilder temp = null;
        try {
            temp = new SentenceBuilder("So <C> is like <A> which is also like <B> so be careful", "f:/IdeaProjects/JargonGenerator/variables.txt");
            System.out.println(temp.getCompletedSentence());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}