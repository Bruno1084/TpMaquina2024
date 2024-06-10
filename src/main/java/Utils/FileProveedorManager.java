package Utils;
import Logica.Clases.Proveedor;
import java.io.*;
import java.util.ArrayList;


public class FileProveedorManager{
    private static String path;
    private static String fileName;
    private static File file;


    static {
        path = "src/main/java/Permanencia/";
        fileName = "Proveedor.txt";
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

    public static ArrayList<Proveedor> readAllLines(){
        ArrayList<Proveedor> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                long dni = Long.parseLong(splitLine[1]);
                String nombre = splitLine[2];
                String direccion = splitLine[3];
                long telefono = Long.parseLong(splitLine[4]);
                int nroLegajo = Integer.parseInt(splitLine[5]);
                String ciudad = splitLine[6];

                Proveedor proveedor = new Proveedor(id, dni, nombre, direccion, telefono, nroLegajo, ciudad);
                data.add(proveedor);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<Proveedor> listaEmpleados){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Proveedor prov : listaEmpleados){
                bufferedWriter.write(prov.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileProveedorManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(Proveedor proveedor){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(proveedor.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error on FileProveedorManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, Proveedor proveedor){
        ArrayList<Proveedor> listaProveedores = readAllLines();
        for (int i = 0; i < listaProveedores.size(); i++) {
            if (listaProveedores.get(i).getId() == id) {
                listaProveedores.set(i, proveedor);
                break;
            }
        }
        writeAllLines(listaProveedores);
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
