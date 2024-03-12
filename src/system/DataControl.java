package system;
import java.io.*;


public abstract class DataControl {
    public abstract String read(String name);

    public abstract int write(String name, String content) throws IOException;
}

