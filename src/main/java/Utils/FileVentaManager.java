package Utils;
import Logica.Clases.Venta;
import java.io.*;
import java.util.ArrayList;


public class FileVentaManager{
    private static String path;
    private static String fileName;
    private static File file;

    static {
        path = "src/main/java/Permanencia/";
        fileName = "Venta.txt";
        file = new File(path, fileName);
        createFile();
    }

    private static void createFile(){
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

    public static ArrayList<Venta> readAllLines(){
        ArrayList<Venta> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                int idProveedor = Integer.parseInt(splitLine[1]);
                String fechaVenta = splitLine[2];
                boolean isDespachado = Boolean.parseBoolean(splitLine[3]);

                Venta venta = new Venta(id, idProveedor, fechaVenta, isDespachado);
                data.add(venta);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<Venta> listaVentas){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Venta vent : listaVentas){
                bufferedWriter.write(vent.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileVentaManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(Venta venta){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(venta.toString());
        } catch (IOException e) {
            System.out.println("Error on FileVentaManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, Venta venta){
        ArrayList<Venta> listaVentas = readAllLines();
        for (int i = 0; i < listaVentas.size(); i++) {
            if (listaVentas.get(i).getIdVenta() == id) {
                listaVentas.set(i, venta);
                break;
            }
        }
        writeAllLines(listaVentas);
    }

    public static void deleteLine(int id) {
        ArrayList<Venta> listaVentas = readAllLines();
        listaVentas.removeIf(venta -> venta.getIdVenta() == id);
        writeAllLines(listaVentas);
    }

    public static Venta getVentaById(int id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String fileLine = bufferedReader.readLine();

            while (fileLine != null) {
                String[] splitLine = fileLine.split(", ");
                int idVenta = Integer.parseInt(splitLine[0]);

                if (idVenta == id) {
                    int idProveedor = Integer.parseInt(splitLine[1]);
                    String fechaVenta = splitLine[2];
                    boolean isDespachado = Boolean.parseBoolean(splitLine[3]);

                    return new Venta(idVenta, idProveedor, fechaVenta, isDespachado);
                }
                fileLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    // Getters and Setters
    public String getPath() {
        return path;
    }
    public static void setPath(String newPath) {
        path = newPath;
        file = new File(path, fileName);
        createFile();
    }

    public String getFileName() {
        return fileName;
    }
    public static void setFileName(String newFileName) {
        fileName = newFileName;
        file = new File(path, fileName);
        createFile();
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        FileVentaManager.file = file;
    }
}
