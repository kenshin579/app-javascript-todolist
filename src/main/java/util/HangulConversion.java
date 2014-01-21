package util;

public class HangulConversion {
    public static String toKor(String en) {
        String kor = null;
        try {
            kor = new String(en.getBytes("8859_1"), "euc-kr");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kor;
    }
}
