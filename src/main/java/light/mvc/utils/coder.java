package light.mvc.utils;

public class coder {
    public static String getCoder(String pre, long count) {
        String code = pre;
        String tmp = "";
        if (count < 10) {
            tmp = "000" + count;
        }
        if (count > 9 && count < 100) {
            tmp = "00" + count;
        }
        if (count > 99 && count < 1000) {
            tmp = "0" + count;
        }
        code = pre + tmp;
        return code;
    }

}
