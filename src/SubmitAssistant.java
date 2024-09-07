import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class SubmitAssistant {
    public static void main(String[] args) throws Exception {
        var clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        var originalCode = clipboard.getContents(null);
        var br = new BufferedReader(new StringReader((String)originalCode.getTransferData(DataFlavor.stringFlavor)));

        var sb = new StringBuilder();
        boolean isComment = false;

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            if (line.startsWith("public class")) {
                line = line.replaceAll(getClassName(line), "Main");
                sb.append(line).append('\n');
            } else if (line.contains("/*")) {
                isComment = true;
            } else if (line.contains("*/")) {
                isComment = false;
            } else if (!line.startsWith("package") && !line.contains("//") && !isComment) {
                sb.append(line).append('\n');
            }
        }

        var submitCode = new StringSelection(sb.toString());
        clipboard.setContents(submitCode, null);
    }

    static String getClassName(String line) {
        return line.replace("public class ", "").replace(" {", "");
    }
}
