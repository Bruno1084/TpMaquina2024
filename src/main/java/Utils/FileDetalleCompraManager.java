package Utils;
import Logica.Clases.DetalleCompra;
import java.io.*;
import java.util.ArrayList;


public class FileDetalleCompraManager {
    private static String path;
    private static String fileName;
    private static File file;

    static {
        path = "src/main/java/Permanencia/";
        fileName = "DetalleCompra.txt";
        file = new File(path, fileName);
        createFile();
    }

    public FileDetalleCompraManager(String path, String fileName){
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

    public static ArrayList<DetalleCompra> readLinesOfCompra(){
        ArrayList<DetalleCompra> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().matches("\\d+\\{")) continue;

                int idCompra = Integer.parseInt(line.trim().split("\\{")[0]);

                while (!(line = bufferedReader.readLine()).trim().equals("}")) {
                    String[] splitLine = line.trim().split(", ");
                    int id = Integer.parseInt(splitLine[0].trim());
                    int idMaterial = Integer.parseInt(splitLine[1].trim());
                    int cantidad = Integer.parseInt(splitLine[2].trim());
                    long peso = Long.parseLong(splitLine[3].trim());
                    long precio = Long.parseLong(splitLine[4].trim());

                    DetalleCompra detalleCompra = new DetalleCompra(id, idMaterial, cantidad, peso, precio, idCompra);
                    data.add(detalleCompra);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<DetalleCompra> listaDetalleCompras){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            int currentIdCompra = -1;
            for (DetalleCompra detalleCompra : listaDetalleCompras) {
                if (detalleCompra.getIdCompra() != currentIdCompra) {
                    if (currentIdCompra != -1) {
                        bufferedWriter.write("}\n");
                    }
                    currentIdCompra = detalleCompra.getIdCompra();
                    bufferedWriter.write(currentIdCompra + "{\n");
                }
                bufferedWriter.write(detalleCompra.getIdDetalleCompra() + ", " +
                        detalleCompra.getIdMaterial() + ", " +
                        detalleCompra.getCantidad() + ", " +
                        detalleCompra.getPeso() + ", " +
                        detalleCompra.getPrecio() + "\n");
            }
            if (currentIdCompra != -1) {
                bufferedWriter.write("}\n");
            }
        } catch (IOException e) {
            System.out.println("Error on FileDetalleVentaManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(DetalleCompra detalleCompra){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(detalleCompra.toString());
        } catch (IOException e) {
            System.out.println("Error on FileDetalleCompraManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void writeVentaWithDetails(int idCompra, ArrayList<DetalleCompra> listaDetalles){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(idCompra + "{\n");
            for (DetalleCompra detalleCompra : listaDetalles) {
                bufferedWriter.write(detalleCompra.toString());
            }
            bufferedWriter.write("}\n");
        } catch (IOException e) {
            System.out.println("Error on FileDetalleCompraManager writeCompraWithDetails method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, DetalleCompra detalleCompra){
        ArrayList<DetalleCompra> listaDetalleCompra = readLinesOfCompra();
        for (int i = 0; i < listaDetalleCompra.size(); i++) {
            if (listaDetalleCompra.get(i).getIdDetalleCompra() == id) {
                listaDetalleCompra.set(i, detalleCompra);
                break;
            }
        }
        writeAllLines(listaDetalleCompra);
    }

    public static void deleteLine(int id) {
        ArrayList<DetalleCompra> listaDetalleCompra = readLinesOfCompra();
        listaDetalleCompra.removeIf(detalleCompra -> detalleCompra.getIdDetalleCompra() == id);
        writeAllLines(listaDetalleCompra);
    }

    public static void deleteVentaGroup(int idCompra) {
        ArrayList<DetalleCompra> listaDetalleCompra = readLinesOfCompra();
        listaDetalleCompra.removeIf(detalleCompra -> detalleCompra.getIdCompra() == idCompra);
        writeAllLines(listaDetalleCompra);
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
