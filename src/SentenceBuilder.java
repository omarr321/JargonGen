import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

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
    private ArrayList<String> sentencePieces = new ArrayList<>();
    private ArrayList<String> sentencePiecesV = new ArrayList<>();

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
        reader = new BufferedReader(new FileReader(this.vaildVarsFilePath));
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

    public String getCompletedSentence() {
        this.evaluate();
        return this.toString();
    }

    private void evaluate() {
        // These goes thought the sentence and breaks it down into its parts
        String temp = "";
        boolean isVar = false;
        char[] tempArr = new char[this.sentence.length()];
        this.sentence.getChars(0, this.sentence.length(), tempArr, 0);
        for (int i = 0; i < tempArr.length; i++) {
            switch(tempArr[i]) {
                case '<':
                    if (temp.length() > 0) {
                        if (isVar) {
                            this.sentencePiecesV.add(temp);
                            this.sentencePieces.add("");
                        } else {
                            this.sentencePiecesV.add("");
                            this.sentencePieces.add(temp);
                        }
                    }
                    temp = "<";
                    isVar = true;
                    break;
                case '>':
                    temp = temp + ">";
                    if (isVar) {
                        this.sentencePiecesV.add(temp);
                        this.sentencePieces.add("");
                    } else {
                        this.sentencePiecesV.add("");
                        this.sentencePieces.add(temp);
                    }
                    isVar = false;
                    temp = "";
                    break;
                default:
                    temp = temp + tempArr[i];
                    break;
            }
        }
        if (temp.length() > 0) {
            this.sentencePiecesV.add("");
            this.sentencePieces.add(temp);
        }

        //TODO: Step 2 - Replace variables with random variables from the file. (IE: <A>, " needs ", <B>, " to function" = "CPU", " needs ", "GPU", " to function")
        for (int i = 0; i < this.sentencePiecesV.size(); i++) {
            String tempV = this.sentencePiecesV.get(i);
            if (tempV != "") {
                if (sentenceVars.checkVar(tempV)) {
                    this.sentencePiecesV.set(i, sentenceVars.getVar(tempV));
                } else {
                    try {
                        String fillIn = getRandVar();
                        this.sentencePiecesV.set(i, fillIn);
                        sentenceVars.addVar(tempV, fillIn);
                    } catch (IOException e) {
                        throw new RuntimeException("ERROR: Can not get variable!");
                    }
                }
            }
        }
    }

    /**
     * Will get a random variable from the file provided
     * @return - The string of the random variable
     * @throws IOException - BufferReader might throw an IOException
     */
    private String getRandVar() throws IOException {
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

    @Override
    public String toString() {
        String tempV = "Variable Array:\n{";
        String[] vArr = this.sentencePiecesV.toArray(new String[this.sentencePiecesV.size()]);
        for(String str : vArr) {
            tempV = tempV + str + ", ";
        }
        tempV = tempV + "}\n";

        String tempS = "String Array:\n{";
        String[] sArr = (String[]) this.sentencePieces.toArray(new String[this.sentencePieces.size()]);
        for(String str : sArr) {
            tempS = tempS + str + ", ";
        }
        tempS = tempS + "}\n";

        return tempV + tempS;
    }
}
