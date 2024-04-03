public interface Formatter {
    public static String space(String str, int num) {
        while(str.length() < num) {
            str += " ";
        }
        return str;
    }
}
