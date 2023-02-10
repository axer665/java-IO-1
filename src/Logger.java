import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private String fileName;
    public Logger(String filename) {
        this.fileName = filename;
    }

    public void writeMessage(String message)
    {
        try (FileWriter writer = new FileWriter(this.fileName, true)) {
            // запись всей строки
            writer.write(message);
            // запись по символам
            writer.append('\n');
            // дозаписываем и очищаем буфер
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
