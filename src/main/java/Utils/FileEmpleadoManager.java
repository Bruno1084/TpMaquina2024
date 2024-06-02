package Utils;
import Logica.Clases.Empleado;
import java.io.*;
import java.util.ArrayList;


public class FileEmpleadoManager {
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;


    public FileEmpleadoManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;

        createFile();
        createFileReader();
        createFileWriter();
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

    private void createFileReader(){
        try{
            this.fileReader = new FileReader(file);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private void createFileWriter(){
        try{
            this.fileWriter = new FileWriter(file, true);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("IO Exeption error on FileManager");
            e.printStackTrace();
        }

    }


    public String readLine(){
        String line = "";
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try{
            line = bufferedReader.readLine();
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return  line;
    }

    public String[] readLineAsArray(){
        String[] arrayLine ={};
        String line;
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try{
            line = bufferedReader.readLine();
            arrayLine = line.split(", ");
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return arrayLine;
    }

    public ArrayList<Empleado> readAllLines(){
        ArrayList<Empleado> data = new ArrayList<>();
        String fileLine;
        String [] splitLine;
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            fileLine = bufferedReader.readLine();
            while (fileLine != null){
                splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                long dni = Long.parseLong(splitLine[1]);
                String nombre = splitLine[2];
                String direccion = splitLine[3];
                long telefono = Long.parseLong(splitLine[4]);
                int nroLegajo = Integer.parseInt(splitLine[5]);
                String fechaIngreso = splitLine[6];

                Empleado empleado = new Empleado(id, dni, nombre, direccion, telefono, nroLegajo, fechaIngreso);
                data.add(empleado);

                fileLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return data;
    }

    public void writeAllLines(ArrayList<Empleado> listaEmpleados){
        for (Empleado emp : listaEmpleados){
            writeLine(emp);
        }
    }
    public void writeLine(Empleado empleado){
        try {
            fileWriter.write(empleado.toString());
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Empleado empleado){
        ArrayList<Empleado> listaEmpleados = readAllLines();


        for (Empleado emp : listaEmpleados){
            System.out.println(emp);
        }
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
