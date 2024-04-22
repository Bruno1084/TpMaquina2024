package Utils;

import Logica.Clases.Empleado;
import java.io.*;
import java.util.ArrayList;


public class FileManager {
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private ArrayList<Empleado> listEmpleados = new ArrayList<>();


    public FileManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;

        createFile();
        createFileReader();
        createFileWriter();
        createBufferedReader();
    }

    public void createFile(){
        this.file = new File(path);
    }

    public void createFileReader(){
        try{
            this.fileReader = new FileReader(file);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public void createFileWriter(){
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

    public void createBufferedReader(){
        this.bufferedReader = new BufferedReader(fileReader);
    }


    public String readLine(){
        String line = "";
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
        try{
            line = bufferedReader.readLine();
            arrayLine = line.split(", ");
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return arrayLine;
    }

    public Empleado readLineAsObject(){
        String[] arrFile = readLineAsArray();
        int id = Integer.parseInt(arrFile[0]);
        int dni = Integer.parseInt(arrFile[1]);
        String nombre = arrFile[2];
        String direccion = arrFile[3];
        int telefono = Integer.parseInt(arrFile[4]);
        int nroLegajo = Integer.parseInt(arrFile[5]);
        String fechaIngreso = arrFile[6];

        Empleado empleado = new Empleado(dni, nombre, direccion, telefono, nroLegajo, fechaIngreso);
        empleado.setId(id);

        return empleado;
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

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public ArrayList<Empleado> getListEmpleados() {
        return listEmpleados;
    }
    public void setListEmpleados(ArrayList<Empleado> listEmpleados) {
        this.listEmpleados = listEmpleados;
    }
}
