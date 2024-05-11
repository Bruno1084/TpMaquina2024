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
    private BufferedReader bufferedReader;


    public FileCompraManager(String path, String fileName){
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

    public ArrayList<Compra> readAllLines(){
        ArrayList<Compra> data = new ArrayList<>();
        String fileLine;
        String [] splitLine;

        try {
            fileLine = bufferedReader.readLine();
            while (fileLine != null){
                splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                String fecha = splitLine[1];
                int clienteId = Integer.parseInt(splitLine[2]);
                String pagado = splitLine[3];
                int empleadoId = Integer.parseInt(splitLine[4]);

                Compra compra = new Compra(id, fecha, clienteId, pagado, empleadoId);
                data.add(compra);

                fileLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return data;
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
}
