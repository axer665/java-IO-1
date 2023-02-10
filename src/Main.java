
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String mainPath = "D://Games";

        Logger temp = new Logger(mainPath+"/temp/temp.txt");

        // определяем массивы для каталогов и файлов
        String[] folders = new String[] {"temp", "src", "res", "savegames"};
        String[] fileLogs = new String[] {"temp.txt"};
        String[] foldersSrc = new String[] {"main", "test"};
        String[] filesSrcMain = new String[] {"Main.java", "Utils.java"};
        String[] foldersRes = new String[] {"drawables", "vectors", "icons"};

        // Создадим конструктор строки, куда будем записывать все действия для лога
        StringBuilder log = new StringBuilder();

        File dir = new File(mainPath);
        if (dir.exists()){
            if (dir.isDirectory()) {
                log.append(folderCreator(folders, mainPath));
                log.append(fileCreator(fileLogs, mainPath+"/temp"));
                log.append(folderCreator(foldersSrc, mainPath+"/src"));
                log.append(fileCreator(filesSrcMain, mainPath+"/src/main"));
                log.append(folderCreator(foldersRes, mainPath+"/res"));
            }

            temp.writeMessage(log.toString());
        }
    }

    private static String folderCreator(String[] folders, String path)
    {
        StringBuilder log = new StringBuilder();
        for (String folder: folders) {
            File mainCatalog = new File(path+"/"+folder);
            if (!mainCatalog.exists()) { // Если каталога еще нет
                if (mainCatalog.mkdir()) { // Попробуем его создать
                    log.append(getCurrentDate() + " : Каталог " + folder + " был создан\n");
                } else {
                    log.append(getCurrentDate() + " : Не удалось создать каталог " + folder + "\n");
                }
            } else {
                log.append(getCurrentDate() + " : Каталог " + folder + " уже существует\n");
            }
        }
        return log.toString();
    }

    private static String fileCreator(String[] files, String path)
    {
        StringBuilder log = new StringBuilder();
        for (String file: files) {
            File logFile = new File(path + "/" + file);
            // создадим новый файл
            try {
                if (logFile.createNewFile()) {
                    log.append(getCurrentDate() + " : Файл " + file + " был создан в каталоге " + path + "\n");
                } else {
                    log.append(getCurrentDate() + " : Файл " + file + " уже существует в каталоге " + path + "\n");
                }
            } catch (IOException ex) {
                log.append(getCurrentDate() + " : Не удалось создать файл " + file + " : " + ex.getMessage() + "\n");
            }
        }

        return log.toString();
    }

    private static String getCurrentDate()
    {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd, hh:mm:ss/SSSS");

        return formatForDateNow.format(dateNow);
    }
}