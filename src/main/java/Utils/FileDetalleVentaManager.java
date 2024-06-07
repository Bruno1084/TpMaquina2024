package Utils;
import Logica.Clases.DetalleVenta;
import java.io.*;
import java.util.ArrayList;


public class FileDetalleVentaManager {
    private String path;
    private String fileName;
    private File file;

    public FileDetalleVentaManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;
        createFile();
    }

    private void createFile(){
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating new file");
                e.printStackTrace();
            }
        }
    }

    public String readLine(){
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line = bufferedReader.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }
        return line;
    }

    public String[] readLineAsArray(){
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

    public ArrayList<DetalleVenta> readLinesOfVenta(){
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

    public void writeAllLines(ArrayList<DetalleVenta> listaDetalleVentas){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (DetalleVenta detalleVenta : listaDetalleVentas){
                bufferedWriter.write(detalleVenta.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error on FileDetalleVentaManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(DetalleVenta detalleVenta){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(detalleVenta.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error on FileDetalleVentaManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, DetalleVenta detalleVenta){
        ArrayList<DetalleVenta> listaDetalleVenta = readLinesOfVenta();
        for (int i = 0; i < listaDetalleVenta.size(); i++) {
            if (listaDetalleVenta.get(i).getIdDetalleVenta() == id) {
                listaDetalleVenta.set(i, detalleVenta);
                break;
            }
        }
        writeAllLines(listaDetalleVenta);
    }

    public void deleteLine(int id) {
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
