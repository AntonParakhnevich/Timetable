import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Timetable {
    public static void main(String[] args) {
        File newTimetable = new File("newTimetable.txt");
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> time = new ArrayList<>();
        Pattern pattern1 = Pattern.compile("\\d{2}:\\d{2}");
        Pattern pattern2 = Pattern.compile("[а-яА-Я]+(\\s[а-яА-Я]+)?");
        String[] strings1 = null;
        String[] strings2 = null;

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("timetable.txt")));
             PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(newTimetable)))) {
            while (scanner.hasNextLine()) {
                line.add(scanner.nextLine());
            }
            for (int i = 0; i < line.size(); i++) {
                strings2 = pattern2.split(line.get(i));
                strings1 = pattern1.split(line.get(i));
                name.addAll(Arrays.asList(strings1));
                time.addAll(Arrays.asList(strings2));
            }
            name.removeAll(Arrays.asList("", null));
            for (int j = 0; j < time.size() - 1; j++) {
                printWriter.write(time.get(j) + "- " + time.get(j + 1) + name.get(j ));
                printWriter.write("\n");
            }
            printWriter.write(time.get(time.size()-1)+name.get(name.size()-1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
