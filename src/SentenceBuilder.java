import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class evaualte the sentence with variables and fills it in
 * @Author Omar M. Radwan
 * @Version 1.0.0
 */
public class SentenceBuilder {

    private VariableMgmt sentenceVars = new VariableMgmt();
    private String sentence = "";
    private String vaildVarsFilePath = "";
    private int varsFileTotalLines = 0;
    private ArrayList sentencePieces = new ArrayList<String>();
    private ArrayList sentencePiecesV = new ArrayList<String>();

    /**
     * Set up the sentence ready for evaluation
     * @param sentence The sentence with the fill in variable as is
     * @param variablesFilePath The Path to the variable list
     * @throws IOException - BufferReader might throw an IOException
     */
    public SentenceBuilder(String sentence, String variablesFilePath) throws IOException {
        this.sentence = sentence;
        this.vaildVarsFilePath = variablesFilePath;

        //Calculate the total number of lines in the variable file provided
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.vaildVarsFilePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: Variable file not found!");
        }
        int temp = 0;
        while(reader.readLine() != null) temp++;
        reader.close();
        this.varsFileTotalLines = temp;
    }

    public String GetCompletedSentence() {
        return "";
    }

    private void Evaluate() {
        //TODO: Step 1 - Break the sentence into it chunks. (IE: <A> needs <B> to function = <A>, " needs ", <B>, " to function")
        //TODO: Step 2 - Replace variables with random variables from the file. (IE: <A>, " needs ", <B>, " to function" = "CPU", " needs ", "GPU", " to function")
        //TODO: Step 3 - Profit!
    }

    /**
     * Will get a random variable from the file provided
     * @return - The string of the random variable
     * @throws IOException - BufferReader might throw an IOException
     */
    private String GetRandVar() throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.vaildVarsFilePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: Variable file not found!");
        }
        int lineToGrab = new Random().nextInt(this.varsFileTotalLines);
        for (int i = 0; i < lineToGrab; i++) {
            reader.readLine();
        }
        String temp = reader.readLine();
        reader.close();
        return temp;
    }
}
