import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        String variablePath = "";
        String sentencesPath = "";
        String connectorsPath = "";

        Scanner userIn = new Scanner(System.in);
        System.out.println("Welcome to Jargon Generator v1.0.0!");
        System.out.println("1 | Generate");
        System.out.println("2 | Quit");
        System.out.println("What would you like to do?");

        int tempIn = 0;
        while(true) {
            System.out.print(">>>");
            String uIn = userIn.nextLine();
            try {
                tempIn = Integer.parseInt(uIn);
                if (tempIn > 2 || tempIn < 1) {
                    System.out.println("Error: Not in the vaild range!");
                } else {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Error: Not a number!");
            }
        }

        switch(tempIn) {
            case 1:
                System.out.println("What is the sentences file? (ex: c:/things/sentences.txt)");
                System.out.print(">>>");
                sentencesPath = userIn.nextLine();

                System.out.println("What is the variables file? (ex: c:/things/variables.txt)");
                System.out.print(">>>");
                variablePath = userIn.nextLine();

                System.out.println("What is the connectors file? (ex: c:/things/connectors.txt)");
                System.out.print(">>>");
                connectorsPath = userIn.nextLine();

                System.out.println("How many cycles do you want to go for?");
                System.out.print(">>>");
                int count = userIn.nextInt();

                String sentenceFull = "";
                for(int i = 0; i < count; i++) {
                    if (!((i == 0) || (i == count))) {
                        sentenceFull = sentenceFull + getRandLine(connectorsPath);
                    }

                    sentenceFull = sentenceFull + new SentenceBuilder(getRandLine(sentencesPath), variablePath).getCompletedSentence();
                }
                System.out.println(sentenceFull);
                break;
            case 2:
                System.out.print("Good Bye!");
                System.exit(0);
                break;
        }
    }

    private static String getRandLine(String file) throws IOException {
        //Calculate the total number of lines in the variable file provided
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: Variable file not found!");
        }
        int temp = 0;
        while(reader.readLine() != null) temp++;
        reader.close();
        int totalLineCount = temp;



        reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ERROR: Variable file not found!");
        }
        int lineToGrab = new Random().nextInt(totalLineCount);
        for (int i = 0; i < lineToGrab; i++) {
            reader.readLine();
        }
        String tempS = reader.readLine();
        reader.close();
        return tempS;
    }
}