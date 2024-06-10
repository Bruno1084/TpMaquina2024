package Utils;
import Logica.Clases.Empleado;
import java.io.*;
import java.util.ArrayList;


public class FileEmpleadoManager{
    private static String path;
    private static String fileName;
    private static File file;


    static {
        path = "src/main/java/Permanencia/";
        fileName = "Empleado.txt";
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

    public static ArrayList<Empleado> readAllLines(){
        ArrayList<Empleado> data = new ArrayList<>();

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
                String fechaIngreso = splitLine[6];
                boolean alta = Boolean.parseBoolean(splitLine[7]);

                Empleado empleado = new Empleado(id, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso, alta);
                data.add(empleado);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static void writeAllLines(ArrayList<Empleado> listaEmpleados){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Empleado emp : listaEmpleados){
                bufferedWriter.write(emp.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public static void writeLine(Empleado empleado){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(empleado.toString());
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeLine method");
            e.printStackTrace();
        }
    }

    public static void editLine(int id, Empleado empleado){
        ArrayList<Empleado> listaEmpleados = readAllLines();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getId() == id) {
                listaEmpleados.set(i, empleado);
                break;
            }
        }
        writeAllLines(listaEmpleados);
    }

    public static void deleteLine(int id) {
        ArrayList<Empleado> listaEmpleados = readAllLines();
        listaEmpleados.removeIf(empleado -> empleado.getId() == id);
        writeAllLines(listaEmpleados);
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
