package system;
import java.io.*;

public class DataWithFile extends DataControl {
    public static DataWithFile dataWithFile;

    public static DataWithFile getInstance() {
        if (dataWithFile == null)
            dataWithFile = new DataWithFile();
        return dataWithFile;
    }
    @Override
    public String read(String name) {
        FileReader reader = null;
        StringBuilder strBuilder = null;
        try {
            File file = new File(name);
            reader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(reader);

            strBuilder = new StringBuilder();
            String temp = null;
            while ((temp = bufReader.readLine()) != null) {
                strBuilder.append(temp + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
        }
        return strBuilder.toString();
    }

    @Override
    public int write(String name, String content) throws IOException {
        FileWriter writer = null;
        try {
            File file = new File(name);
            writer = new FileWriter(file, true);
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }
}
