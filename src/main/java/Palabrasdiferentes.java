import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;
//C:\Users\erubi\Desktop\Proyecto_phishing\src\textos

public class Palabrasdiferentes {
    public void readFile(File file){
        int contador = 0;
        try{
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\erubi\\Desktop\\Proyecto_phishing\\src\\textos\\"+file));
        System.out.println("Archivo leido.");
        System.out.println("----------");
        String linea = br.readLine();
        while (linea != null) {
            System.out.println("texto: "+linea);
            String[] palabras = linea.split(" ");
            contador += palabras.length;
            linea = br.readLine();
            System.out.println("El archivo tiene "+contador+" palabras.");
            System.out.println(" ");
        }
        br.close();
    } catch (
    FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public void printUniqueWords(File file){
        int contador = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\erubi\\Desktop\\Proyecto_phishing\\src\\textos\\"+file));
        String str= br.readLine();
        String[] words = str.split("\\W");
            System.out.println("Palabras unicas: ");
        for (int i = 0; i < words.length; i++) {
            contador=1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equalsIgnoreCase(words[j])) {
                    contador++;
                    words[j] = "";
                }
            }
            if (contador == 1 && words[i] != "")
                System.out.print("/"+words[i]);
        }
            System.out.println(" ");
    } catch (
    FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void StopwordsRemover(File file){
        System.out.println(" ");
        System.out.println("Texto sin stop words: ");
        int k=0;
        ArrayList<String> wordsList = new ArrayList<String>();
        String sCurrentLine;
        String[] stopwords = new String[2000];
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\erubi\\Desktop\\Proyecto_phishing\\src\\StopWords.txt"));
            while ((sCurrentLine = br.readLine()) != null){
                stopwords[k]=sCurrentLine;
                k++;
            }
            BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\erubi\\Desktop\\Proyecto_phishing\\src\\textos\\"+file));
            String str= bf.readLine();
            StringBuilder builder = new StringBuilder(str);
            String[] words = builder.toString().split("\\s");
            for (String word : words){
                wordsList.add(word);
            }
            for(int ii = 0; ii < wordsList.size(); ii++){
                for(int jj = 0; jj < k; jj++){
                    if(stopwords[jj].contains(wordsList.get(ii).toLowerCase())){
                        wordsList.remove(ii);
                        break;
                    }
                }
            }
            for (String sttr : wordsList){
                System.out.print(sttr+" ");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void  contadorphishing(File file){

    }

    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       String Directorio= sc.nextLine();
       try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(Directorio))) {
           for (Path file: stream) {
               System.out.println("Archivo a analizar: "+file.getFileName());
               System.out.println(" ");
               Palabrasdiferentes lectura= new Palabrasdiferentes();
               Palabrasdiferentes unicas= new Palabrasdiferentes();
               Palabrasdiferentes stop= new Palabrasdiferentes();
               File filee= new File(String.valueOf((file.getFileName())));
               lectura.readFile(filee);
               unicas.printUniqueWords(filee);
               stop.StopwordsRemover(filee);
           }
       } catch (IOException e) {
           System.err.println("Error: "+e.getMessage());
       }
        System.out.println(" ");
   }
}
