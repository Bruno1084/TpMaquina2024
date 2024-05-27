package Utils;
import Logica.Clases.DetalleVenta;
import java.io.*;
import java.util.ArrayList;


public class FileDetalleVentaManager {
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;


    public FileDetalleVentaManager(String path, String fileName){
        this.path = path;
        this.fileName = fileName;

        createFile();
        createFileReader();
        createFileWriter();
        createBufferedReader();
    }


    public void createFile(){
        this.file = new File(path, fileName);
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

    public ArrayList<DetalleVenta> readAllLines(){
        ArrayList<DetalleVenta> data = new ArrayList<>();
        String fileLine;
        String [] splitLine;

        try {
            fileLine = bufferedReader.readLine();
            while (fileLine != null){
                if(fileLine.contains("{")){
                    fileLine = bufferedReader.readLine();
                    if (fileLine.contains("}")){
                        continue;
                    }else {
                        splitLine= fileLine.split(" ,");
                        int idDetalleVenta = Integer.parseInt(splitLine[0]);
                        int idMaterial = Integer.parseInt(splitLine[1]);
                        int cantidad = Integer.parseInt(splitLine[2]);
                        Long peso = Long.parseLong(splitLine[3]);
                        Long precio = Long.parseLong(splitLine[4]);

                        DetalleVenta detalleVenta = new DetalleVenta(idDetalleVenta, idMaterial, cantidad, peso, precio);
                        data.add(detalleVenta);
                    }
                }



                fileLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return data;
    }

    public void searchGroupOfDetails(int id){
        /*Binary Search Implementation
           binarySeach(arr, target){
            int l = 0;
            int m = arr.length() -1;

            while(l <= m){
                int mid = Math.floor((l + r)/2);

                if(arr[mid] == target){ return mid };
                else if(arr[mid] < target){
                    l = midd +1;
                }else{
                    r = midd -1;
                }
            }
            return l;
        * */
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
