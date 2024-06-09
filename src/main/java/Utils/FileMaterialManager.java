package Utils;
import Logica.Clases.Material;
import java.io.*;
import java.util.ArrayList;


public class FileMaterialManager{
    private static String path;
    private static String fileName;
    private static File file;


    static {
        path = "src/main/java/Permanencia/";
        fileName = "Material.txt";
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

    public static ArrayList<Material> readAllLines(){
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

                Material material = new Material(id, nombre, descripcion, tipoMedida, stock, precioCompra, precioVenta);
                data.add(material);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<Material> listaMateriales){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Material mat : listaMateriales){
                bufferedWriter.write(mat.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(Material material){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(material.toString());
        } catch (IOException e) {
            System.out.println("Error on FileMaterialManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, Material material){
        ArrayList<Material> listaMateriales = readAllLines();
        for (int i = 0; i < listaMateriales.size(); i++) {
            if (listaMateriales.get(i).getId() == id) {
                listaMateriales.set(i, material);
                break;
            }
        }
        writeAllLines(listaMateriales);
    }

    public static void deleteLine(int id) {
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
}
