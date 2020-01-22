import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Number {
    public static void main(String[] args) {
        String number="+375325395615";
        Pattern pattern = Pattern.compile("(\\+375)(25|29|33|44)(\\d{3})(\\d{2})(\\d{2})");
        Matcher matcher = pattern.matcher(number);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer,
                    String.valueOf(matcher.group(1) + " (" + matcher.group(2) + ") " + matcher.group(3) + "-" + matcher.group(4) + "-" + matcher.group(5)));
        }
        System.out.println(stringBuffer);
    }


}
