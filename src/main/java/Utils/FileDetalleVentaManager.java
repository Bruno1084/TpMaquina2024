package Utils;
import Logica.Clases.DetalleVenta;
import java.io.*;
import java.util.ArrayList;


public class FileDetalleVentaManager {
    private static String path;
    private static String fileName;
    private static File file;

    static {
        path = "src/main/java/Permanencia/";
        fileName = "DetalleVenta.txt";
        file = new File(path, fileName);
        createFile();
    }

    public FileDetalleVentaManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line = bufferedReader.readLine();
        } catch (IOException e){
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

    public static ArrayList<DetalleVenta> readLinesOfVenta(){
        ArrayList<DetalleVenta> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().matches("\\d+\\{")) continue;

                int idVenta = Integer.parseInt(line.trim().split("\\{")[0]);

                while (!(line = bufferedReader.readLine()).trim().equals("}")) {
                    String[] splitLine = line.trim().split(", ");
                    int id = Integer.parseInt(splitLine[0].trim());
                    int idMaterial = Integer.parseInt(splitLine[1].trim());
                    int cantidad = Integer.parseInt(splitLine[2].trim());
                    long peso = Long.parseLong(splitLine[3].trim());
                    long precio = Long.parseLong(splitLine[4].trim());

                    DetalleVenta detalleVenta = new DetalleVenta(id, idMaterial, cantidad, peso, precio, idVenta);
                    data.add(detalleVenta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<DetalleVenta> listaDetalleVentas){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            int currentIdVenta = -1;
            for (DetalleVenta detalleVenta : listaDetalleVentas) {
                if (detalleVenta.getIdVenta() != currentIdVenta) {
                    if (currentIdVenta != -1) {
                        bufferedWriter.write("}\n");
                    }
                    currentIdVenta = detalleVenta.getIdVenta();
                    bufferedWriter.write(currentIdVenta + "{\n");
                }
                bufferedWriter.write(detalleVenta.getIdDetalleVenta() + ", " +
                        detalleVenta.getIdMaterial() + ", " +
                        detalleVenta.getCantidad() + ", " +
                        detalleVenta.getPeso() + ", " +
                        detalleVenta.getPrecio() + "\n");
            }
            if (currentIdVenta != -1) {
                bufferedWriter.write("}\n");
            }
        } catch (IOException e) {
            System.out.println("Error on FileDetalleVentaManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(DetalleVenta detalleVenta){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(detalleVenta.toString());
        } catch (IOException e) {
            System.out.println("Error on FileDetalleVentaManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void writeVentaWithDetails(int idVenta, ArrayList<DetalleVenta> listaDetalles){
        ArrayList<DetalleVenta> allDetalleVentas = readLinesOfVenta();
        allDetalleVentas.addAll(listaDetalles); // Add the new list of detalleVentas

        writeAllLines(allDetalleVentas); // Write everything back to the file
    }

    public static void editLine(int id, DetalleVenta detalleVenta){
        ArrayList<DetalleVenta> listaDetalleVenta = readLinesOfVenta();
        for (int i = 0; i < listaDetalleVenta.size(); i++) {
            if (listaDetalleVenta.get(i).getIdDetalleVenta() == id) {
                listaDetalleVenta.set(i, detalleVenta);
                break;
            }
        }
        writeAllLines(listaDetalleVenta);
    }

    public static void deleteLine(int id) {
        ArrayList<DetalleVenta> listaDetalleVenta = readLinesOfVenta();
        listaDetalleVenta.removeIf(detalleVenta -> detalleVenta.getIdDetalleVenta() == id);
        writeAllLines(listaDetalleVenta);
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