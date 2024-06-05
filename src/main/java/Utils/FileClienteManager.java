package Utils;
import Logica.Clases.Cliente;
import java.io.*;
import java.util.ArrayList;


public class FileClienteManager implements  FileManagerUtils<Cliente>{
    private String path;
    private String fileName;
    private File file;
    private FileReader fileReader;
    private FileWriter fileWriter;


    public FileClienteManager(String path, String fileName){
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

    public void createFileReader(){
        try{
            this.fileReader = new FileReader(file);
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
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

    public ArrayList<Cliente> readAllLines(){
        ArrayList<Cliente> data = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String fileLine = bufferedReader.readLine();

            while(fileLine != null){
                String[] splitLine = fileLine.split(", ");
                int id = Integer.parseInt(splitLine[0]);
                long dni = Long.parseLong(splitLine[1]);
                String nombre = splitLine[2];
                String direccion = splitLine[3];
                long telefono = Long.parseLong(splitLine[4]);

                Cliente cliente = new Cliente(id, dni, nombre, direccion, telefono);
                data.add(cliente);

                fileLine = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public void writeAllLines(ArrayList<Cliente> listaClientes){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Cliente cli : listaClientes){
                bufferedWriter.write(cli.toString());
            }
        } catch (IOException e) {
            System.out.println("Error on FileEmpleadoManager writeAllLines method");
            e.printStackTrace();
        }
    }

    public void writeLine(Cliente cliente){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(cliente.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Error on FileClienteManager writeLine method");
            e.printStackTrace();
        }
    }

    public void editLine(int id, Cliente cliente){
        ArrayList<Cliente> listaClientes = readAllLines();
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId() == id) {
                listaClientes.set(i, cliente);
                break;
            }
        }
        writeAllLines(listaClientes);
    }

    public void deleteLine(int id) {
        ArrayList<Cliente> listaClientes = readAllLines();
        listaClientes.removeIf(cliente -> cliente.getId() == id);
        writeAllLines(listaClientes);
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
