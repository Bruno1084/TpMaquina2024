package Utils;
import Logica.Clases.Compra;
import java.io.*;
import java.util.ArrayList;


public class FileCompraManager {
    private static String path;
    private static String fileName;
    private static File file;

    static {
        path = "src/main/java/Permanencia/";
        fileName = "Compra.txt";
        file = new File(path, fileName);
        createFile();
    }

    public static void createFile(){
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file");
                e.printStackTrace();
            }
        }
    }

    public static String readLine(){
        String line = "";

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line = bufferedReader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        return line;
    }

    public static String[] readLineAsArray(){
        String[] arrayLine = {};
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            if (line != null) {
                arrayLine = line.split(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayLine;
    }

    public static ArrayList<Compra> readAllLines(){
        ArrayList<Compra> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                String fecha = splitLine[1];
                int idCliente = Integer.parseInt(splitLine[2]);
                String pagado = splitLine[3];
                int idEmpleado = Integer.parseInt(splitLine[4]);

                Compra compra = new Compra(id, fecha, idCliente, pagado, idEmpleado);
                data.add(compra);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<Compra> readAllLinesByClient(int idSearch){
        ArrayList<Compra> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");

                if (String.valueOf(idSearch).equals(splitLine[2])){
                    int id = Integer.parseInt(splitLine[0]);
                    String fecha = splitLine[1];
                    int idCliente = Integer.parseInt(splitLine[2]);
                    String pagado = splitLine[3];
                    int idEmpleado = Integer.parseInt(splitLine[4]);

                    Compra compra = new Compra(id, fecha, idCliente, pagado, idEmpleado);
                    data.add(compra);
                }
                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return data;
    }

    public static void writeAllLines(ArrayList<Compra> listaCompras){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Compra comp : listaCompras){
                bufferedWriter.write(comp.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileCompraManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(Compra compra){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(compra.toString());
        } catch (IOException e) {
            System.out.println("Error on FileCompraManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, Compra compra){
        ArrayList<Compra> listaCompras = readAllLines();
        for (int i = 0; i < listaCompras.size(); i++) {
            if (listaCompras.get(i).getId() == id) {
                listaCompras.set(i, compra);
                break;
            }
        }
        writeAllLines(listaCompras);
    }

    public static void deleteLine(int id) {
        ArrayList<Compra> listaCompras = readAllLines();
        listaCompras.removeIf(compra -> compra.getId() == id);
        writeAllLines(listaCompras);
    }

    // Getters and Setters
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
}
