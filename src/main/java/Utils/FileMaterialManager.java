package Utils;
import Logica.Clases.Material;
import java.io.*;
import java.util.ArrayList;


public class FileMaterialManager implements FileManagerUtils<Material>{
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;

    public FileMaterialManager(String path, String fileName){
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

    public ArrayList<Material> readAllLines(){
        ArrayList<Material> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                String nombre = splitLine[1];
                String descripcion = splitLine[2];
                String tipoMedida = splitLine[3];
                int stock = Integer.parseInt(splitLine[4]);
                float precioCompra = Float.parseFloat(splitLine[5]);
                float precioVenta = Float.parseFloat(splitLine[6]);
                boolean alta = Boolean.parseBoolean(splitLine[7]);

                Material material = new Material(id, nombre, descripcion, tipoMedida, stock, precioCompra, precioVenta, alta);
                data.add(material);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public void writeAllLines(ArrayList<Material> listaMateriales){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Material mat : listaMateriales){
                bufferedWriter.write(mat.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(Material material){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(material.toString());
        } catch (IOException e) {
            System.out.println("Error on FileMaterialManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Material material){
        ArrayList<Material> listaMateriales = readAllLines();
        for (int i = 0; i < listaMateriales.size(); i++) {
            if (listaMateriales.get(i).getId() == id) {
                listaMateriales.set(i, material);
                break;
            }
        }
        writeAllLines(listaMateriales);
    }

    public void deleteLine(int id) {
        ArrayList<Material> listaMateriales = readAllLines();
        listaMateriales.removeIf(material -> material.getId() == id);
        writeAllLines(listaMateriales);
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
