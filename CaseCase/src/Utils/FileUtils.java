package Utils;

import Models.IParseModel;
import Models.Show;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static <E> List<E> readData(String fileUser, Class<E> clazz) {
        List<E> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileUser);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Object obj = clazz.newInstance();
                IParseModel<E> iParseModel = (IParseModel) obj;
                list.add(iParseModel.parse(line));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static <T> void writeData(String file, List<T> list) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (T t : list) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeData(String fileShow, Class<Show> showClass) {
    }
}
