package alexeykrasnov.htctest;

import java.util.List;

public final class ListUtilities {
    public static String parseToString(List<String> list) {
        return list.toString().substring(1, list.toString().length() - 1);
    }
}