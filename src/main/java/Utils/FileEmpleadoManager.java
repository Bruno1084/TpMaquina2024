package Utils;
import Logica.Clases.Empleado;
import java.io.*;
import java.util.ArrayList;


public class FileEmpleadoManager implements FileManagerUtils<Empleado>{
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;

    public FileEmpleadoManager(String path, String fileName){
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

    public ArrayList<Empleado> readAllLines(){
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

    public void writeAllLines(ArrayList<Empleado> listaEmpleados){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Empleado emp : listaEmpleados){
                bufferedWriter.write(emp.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(Empleado empleado){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(empleado.toString());
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Empleado empleado){
        ArrayList<Empleado> listaEmpleados = readAllLines();
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getId() == id) {
                listaEmpleados.set(i, empleado);
                break;
            }
        }
        writeAllLines(listaEmpleados);
    }

    public void deleteLine(int id) {
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
