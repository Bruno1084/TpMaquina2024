package Utils;

import java.util.ArrayList;

public interface FileManagerUtils<T>{

    ArrayList<T> readAllLines();

    void writeAllLines(ArrayList<T> items);

    void writeLine(T item);

    void editLine(int id, T item);

    String readLine();

    String[] readLineAsArray();
}
