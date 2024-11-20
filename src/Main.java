import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";
        String outputFilePath = "output.txt";

        try {
            FileReaderUtil fileReader = new FileReaderUtil();
            String content = fileReader.readFile(inputFilePath);

            WordProcessor wordProcessor = new WordProcessor();
            Set<String> uniqueWords = wordProcessor.processWords(content);

            FileWriterUtil fileWriter = new FileWriterUtil();
            fileWriter.writeWords(outputFilePath, uniqueWords);

            System.out.println("Записано в " + outputFilePath);
        } catch (Exception e) {
            System.out.println("Что-то не так: " + e.getMessage());
        }
    }
}

class FileReaderUtil {
    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        }

        return content.toString();
    }
}

class WordProcessor {
    public Set<String> processWords(String content) {
        Set<String> uniqueWords = new TreeSet<>();

        StringTokenizer tokenizer = new StringTokenizer(content, " ,.!?;:\"()[]{}<>-");

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase();
            uniqueWords.add(word);
        }

        return uniqueWords;
    }
}

class FileWriterUtil {
    public void writeWords(String filePath, Set<String> uniqueWords) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String word : uniqueWords) {
                writer.write(word);
                writer.newLine();
            }
        }
    }
}
