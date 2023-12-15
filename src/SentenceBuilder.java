import java.io.FileReader;
import java.util.ArrayList;

public class SentenceBuilder {

    private VariableMgmt sentenceVars = new VariableMgmt();
    private String sentence = "";
    private FileReader vaildVars = null;
    private ArrayList sentencePieces = new ArrayList<String>();
    private ArrayList sentencePiecesV = new ArrayList<String>();

    public SentenceBuilder(String sentence, FileReader variables){
        this.sentence = sentence;
        this. vaildVars = variables;
    }

    public String GetCompletedSentence() {
        return "";
    }

    private void Evaluate() {}

    private String GetRandVar() {
        return "";
    }
}
