package Utils;
import Logica.Clases.Compra;
import java.io.*;
import java.util.ArrayList;


public class FileCompraManager {
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;


    public FileCompraManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;

        createFile();
    }

    public void createFile(){
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

    public ArrayList<Compra> readAllLines(){
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

    public void writeAllLines(ArrayList<Compra> listaCompras){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Compra comp : listaCompras){
                bufferedWriter.write(comp.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileCompraManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(Compra compra){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(compra.toString());
        } catch (IOException e) {
            System.out.println("Error on FileCompraManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Compra compra){
        ArrayList<Compra> listaCompras = readAllLines();
        for (int i = 0; i < listaCompras.size(); i++) {
            if (listaCompras.get(i).getId() == id) {
                listaCompras.set(i, compra);
                break;
            }
        }
        writeAllLines(listaCompras);
    }

    public void deleteLine(int id) {
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
