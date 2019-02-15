/**
*@version cs251 Lab002 Date: 10/23/2018
*@author Xiaomeng Li
**/
import java .io.*;
import java.util.*;

/**class LineSorter implements Comparator interface, imports the 
"input.txt", reads the data,sort them by length and character, then
exports them to "output.txt". 
To use the code, type in "java LineSorter input.txt output.txt" **/
public class LineSorter implements Comparator<String> {

    @Override
    /** compare method to compare strings by length 
    and then by character**/
    public int compare(String str1, String str2) {
        /**
            @param String str1 and String st2
            @return the results after comparison
            **/
        if (str1.length()!=str2.length()) {
            return str1.length()-str2.length();
        }
        return str1.compareTo(str2);
    }

    /** main method to read the file, store the string in an arraylist
    then sort them by length abd character. IOException is necessary
    for file's reading in**/
    public static void main(String[] args) throws IOException {
        PrintWriter out = null ;
        try {
            //File f = new File("input.txt");
            File f = new File(args[0]);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String line = "";

            //out = new PrintWriter (new FileWriter ("output.txt"));
            out = new PrintWriter (new FileWriter (args[1]));
            List<String> tempList = new ArrayList<>();
            while ((line = b.readLine()) != null) {
                //if (line.isEmpty() || !(line.charAt(0)=='#')) {
                if (!line.startsWith("#")){
                    tempList.add(line);
                }
            }
            Collections.sort(tempList,new LineSorter());
            int size = tempList.size();

            for(String str: tempList){
                out.println(str);
            }


        } catch (IOException x) {
            System.err.println(x);
        } finally {
            if (out != null ){out. close ();}
        }

    }
}
