import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    public static void main(String[] args) {
        File newTime = new File("newTime");
        Pattern pattern = Pattern.compile("\\d{2}");
        Pattern pattern1 = Pattern.compile("[а-яА-Я]+(\\s[а-яА-Я]+)?");
        ArrayList<String> time = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        ArrayList<Integer> time1 = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("timetable.txt")));
             PrintWriter printWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(newTime)))) {

            while (scanner.hasNextLine()) {
                Matcher matcher = pattern1.matcher(scanner.nextLine());
                while (matcher.find()) {
                    name.add(matcher.group());
                }
            }
            Scanner scanner1 = new Scanner(new BufferedReader(new FileReader("timetable.txt")));
            while (scanner1.hasNextLine()) {
                Matcher matcher = pattern.matcher(scanner1.nextLine());
                while (matcher.find()) {
                    time.add(matcher.group());
                }
            }
            ArrayList<Integer> timeInMinutes = new ArrayList<>();
            for (int i = 0; i < time.size(); i += 2) {
                timeInMinutes.add(Integer.parseInt(time.get(i)) * 60 + Integer.parseInt(time.get(i + 1)));
            }
            int countTime = 0;
            for (int i = 1; i < timeInMinutes.size(); i++) {
                time1.add(timeInMinutes.get(i) - timeInMinutes.get(i - 1));
                countTime += time1.get(i-1);
            }
            for (int i = 0; i < time1.size(); i++) {
                int percent = time1.get(i) * 100 / countTime;
                printWriter.write(name.get(i) + ": " + time1.get(i) + " минут " + percent + "%\n");
            }
            printWriter.write(name.get(name.size()-1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
