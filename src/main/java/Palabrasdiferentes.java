import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Palabrasdiferentes {
    public String[] readFile(File file){
        String lines= "";
        try{
            Scanner sc=new Scanner(file);
            while(sc.hasNextLine()){
                lines +=sc.nextLine();
            }
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        return lines.split(" ");
    }

    public static void main(String[] args){
    final String Directorio="C:\\Users\\erubi\\Desktop\\Proyecto_phishing\\Textos";
    try(DirectoryStream<Path> ds= Files.newDirectoryStream(Paths.get(Directorio))){
        for(Path ruta: ds){
            System.out.println(ruta.getFileName());
            Palabrasdiferentes lectura= new Palabrasdiferentes();
            File file= new File(String.valueOf(ruta.getFileName()));
            String[] lines= lectura.readFile(file);
            int words= lines.length;
            System.out.println("El archivo tiene "+words+" palabras, de las cuales son "+" palabras diferentes.");
        }
    } catch (IOException e) {
        System.err.println("Error: "+e.getMessage());
    }
    }
}




