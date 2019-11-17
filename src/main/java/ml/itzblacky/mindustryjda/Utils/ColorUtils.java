package ml.itzblacky.mindustryjda.Utils;

import java.util.ArrayList;
import java.util.List;

public class ColorUtils {
    private static List<String> colors = new ArrayList<>();

    static {
        colors.add("[clear]");
        colors.add("[black]");
        colors.add("[white]");
        colors.add("[light_gray]");
        colors.add("[gray]");
        colors.add("[dark_gray]");
        colors.add("[blue]");
        colors.add("[navy]");
        colors.add("[royal]");
        colors.add("[slate]");
        colors.add("[sky]");
        colors.add("[cyan]");
        colors.add("[teal]");
        colors.add("[green]");
        colors.add("[acid]");
        colors.add("[lime]");
        colors.add("[forest]");
        colors.add("[olive]");
        colors.add("[yellow]");
        colors.add("[gold]");
        colors.add("[goldenrod]");
        colors.add("[orange]");
        colors.add("[brown]");
        colors.add("[tan]");
        colors.add("[brick]");
        colors.add("[red]");
        colors.add("[scarlet]");
        colors.add("[coral]");
        colors.add("[salmon]");
        colors.add("[pink]");
        colors.add("[magenta]");
        colors.add("[purple]");
        colors.add("[violet]");
        colors.add("[maroon]");
    }

    public static String removeColorString(String str) {
        if (!(str.contains("[")) | !(str.contains("]"))) return str;
        for (String s : colors) {
            if (!(str.toLowerCase().contains(s))) continue;
            str = str.replace(s, "");
        }
        return str;
    }
}
