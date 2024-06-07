package Utils;
import Logica.Clases.Venta;
import java.io.*;
import java.util.ArrayList;


public class FileVentaManager implements FileManagerUtils<Venta> {
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;


    public FileVentaManager(String path, String fileName){
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

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            line = bufferedReader.readLine();
        }catch (IOException e){
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

    public ArrayList<Venta> readAllLines(){
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

    public void writeAllLines(ArrayList<Venta> listaVentas){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Venta vent : listaVentas){
                bufferedWriter.write(vent.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileVentaManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(Venta venta){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(venta.toString());
        } catch (IOException e) {
            System.out.println("Error on FileVentaManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Venta venta){
        ArrayList<Venta> listaVentas = readAllLines();
        for (int i = 0; i < listaVentas.size(); i++) {
            if (listaVentas.get(i).getIdVenta() == id) {
                listaVentas.set(i, venta);
                break;
            }
        }
        writeAllLines(listaVentas);
    }

    public void deleteLine(int id) {
        ArrayList<Venta> listaVentas = readAllLines();
        listaVentas.removeIf(venta -> venta.getIdVenta() == id);
        writeAllLines(listaVentas);
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

    public FileReader getFileReader() {
        return fileReader;
    }
    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }
    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }
}
