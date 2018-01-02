package rapl;

import com.opencsv.CSVReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class jRAPLWorker {

    private static String reportPath = System.getProperty("user.dir") + "/src/test/java/rapl/report/";
    private static String targetPath = System.getProperty("user.dir") + "/target/jrapl-reports/";
    private static String htmlPath = System.getProperty("user.dir") + "/src/test/java/rapl/report/report-template.html";
    private static long start;
    private static long end;
    private static String name;
    private static double[] start_stats;
    private static double[] end_stats;
    private static int nrtests = 0;
    private static int nrwarnings = 0;
    private static int nrdeprecated = 0;

    static {
//        try {
//            File file = new File(reportPath + "jrapl.csv");
//            file.delete();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public static void start(String _name){
        EnergyCheckUtils.ProfileInit();
    	start = System.currentTimeMillis();
    	start_stats = EnergyCheckUtils.getEnergyStats();
    	name =  _name;
    } 

    public static void end(){
    	end = System.currentTimeMillis();
    	end_stats = EnergyCheckUtils.getEnergyStats();
    	report();
    	EnergyCheckUtils.ProfileDealloc();
    }

    public static void report(){

        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(new File(reportPath + "jrapl.csv"), true /* append = true */));

            BufferedReader br = new BufferedReader(new FileReader(reportPath + "jrapl.csv"));
            if (br.readLine() == null) {
                pw.append("class_name,method_name,time_elapsed,dram,cpu,package" + "\n");
            }
            pw.append(name +"," + (end - start) + "," + ((end_stats[0] - start_stats[0]) / 10.0) + "," + ((end_stats[1] - start_stats[1]) / 10.0) + "," + ((end_stats[2] - start_stats[2]) / 10.0) + "\n");
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateTargetreports(){

        File directory = new File(targetPath);
        if (! directory.exists()){
            directory.mkdir();
        }
        try {
            File file = new File(targetPath + "index.html");
            file.delete();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            String html;
            BufferedReader bufferreader = new BufferedReader(new FileReader(htmlPath));
            html = bufferreader.readLine();
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(targetPath + "index.html"), true /* append = true */));
            while (html != null) {
                if (html.contains("@INSERTDATA")) {
                    pw.append(generateHtml());
                } else if (html.contains("@INSERTNUMBEROFWARNINGS")) {
                    pw.append("<p><h2>" + nrwarnings+ "</h2></p>").append("\n");
                } else if (html.contains("@INSERTNUMBEROFTESTS")) {
                    pw.append("<p><h2>" + nrtests+ "</h2></p>").append("\n");
                } else if (html.contains("@INSERTNUMBEROFDEPRECATED")){
                    pw.append("<p><h2>" + nrdeprecated+ "</h2></p>").append("\n");
                }
                else{
                    pw.append(html).append("\n");
                }
                html = bufferreader.readLine();
            }
            pw.close();

            try {
                Files.copy(new File(reportPath + "css").toPath(), new File(targetPath + "css").toPath(), StandardCopyOption.REPLACE_EXISTING);
                Files.copy(new File(reportPath + "js").toPath(), new File(targetPath + "js").toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {}

            Files.copy(new File(reportPath + "jrapl.csv").toPath(), new File(targetPath + "jrapl.csv").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "jrapl-old.csv").toPath(), new File(targetPath + "jrapl-old.csv").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "css/bootstrap.min.css").toPath(), new File(targetPath + "css/bootstrap.min.css").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "js/bootstrap.min.js").toPath(), new File(targetPath + "js/bootstrap.min.js").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "js/canvasjs.min.js").toPath(), new File(targetPath + "js/canvasjs.min.js").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "js/popper.min.js").toPath(), new File(targetPath + "js/popper.min.js").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "js/jquery-3.2.1.slim.min.js").toPath(), new File(targetPath + "js/jquery-3.2.1.slim.min.js").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(new File(reportPath + "glyphicons-exclamation-sign.png").toPath(), new File(targetPath + "glyphicons-exclamation-sign.png").toPath(), StandardCopyOption.REPLACE_EXISTING);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateHtml(){
        List<String[]> csv = getCombinedCSVs(reportPath + "jrapl-old.csv",reportPath + "jrapl.csv");
        removeDuplicatedLines(csv);
        HashMap<String, List<String[]>> classes = new HashMap<>();
        for (String[] str : csv) {
            if (classes.containsKey(str[0])){
                classes.get(str[0]).add(str.clone());
            } else {
                List<String[]> aux  = new ArrayList<>();
                aux.add(str);
                classes.put(str[0], aux);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        String sign15 = "      <img src=\"glyphicons-exclamation-sign.png\" height=\"15\" width=\"15\">";
        String sign10 = "      <img src=\"glyphicons-exclamation-sign.png\" height=\"10\" width=\"10\">";
        String sign15Deprecated = "      <img src=\"glyphicons-exclamation-sign.png\" height=\"15\" width=\"15\" style=\"filter: drop-shadow(0px 0px 3px red);\">";
        String sign10Deprecated = "      <img src=\"glyphicons-exclamation-sign.png\" height=\"10\" width=\"10\" style=\"filter: drop-shadow(0px 0px 3px red);\">";

        for (Map.Entry<String, List<String[]>> map: classes.entrySet()) {
            String warning = containsWarnings(map.getValue());
            stringBuilder.append("<div class=\"single category\" style=\"padding-left: 1em;\"> \n");
            stringBuilder.append("   <a href=\"#\"><h3 class=\"side-title\" data-toggle=\"collapse\" data-target=\"#"+ map.getKey() +"\">" + map.getKey() + ( warning == null ? "" : (warning.equals("warn") ?  sign15 : sign15Deprecated)) + "</h3></a>  \n");
            stringBuilder.append("   <ul class=\"list-unstyled collapse\" id=\"" + map.getKey() + "\"> \n");
            for (String[] str2: map.getValue()) {
                stringBuilder.append("     <li>\n        <a href=\"#\" title=\"\" onclick=\"chart('" + str2[1]  + "', " + str2[2] + "," + str2[3] +", " + str2[4] +"," + str2[5] + ","+ str2[6] +"," + str2[7] +", " + str2[8] + "," + str2[9] +");\">" + str2[1] + (str2[10] == null ? "" : (str2[10].equals("warn") ?  sign10 : sign10Deprecated)) + " </a>\n     </li> \n");
            }
            stringBuilder.append("   </ul> \n");
            stringBuilder.append("</div>  \n");
        }
        return stringBuilder.toString();
    }

    private static String containsWarnings(List<String[]> values){

        boolean warn = false;
        boolean dep = false;
        for (String [] str: values) {
            nrtests++;
            if (str[10] != null) {
                if (str[10].equals("deprecated")) {
                    nrdeprecated++;
                    dep = true;
                } else {
                    nrwarnings++;
                    warn = true;
                }
            }
        }
        return dep ? "deprecated" : (warn ? "warn": null);
    }

    public static List<String[]> getCombinedCSVs(String _old, String _actual) {
        try {
            List<String[]> actual = new CSVReader(new FileReader(_actual)).readAll();
            List<String[]> old = new CSVReader(new FileReader(_old)).readAll();
            List<String[]> combined = new ArrayList<>();

            actual.remove(0);
            old.remove(0);

            for (String[] str: actual) {
                String[] aux = new String[11];
                aux[0]= str[0];
                aux[1]= str[1];
                aux[2]= null;
                aux[3]= null;
                aux[4]= null;
                aux[5]= null;
                aux[6]= str[2];
                aux[7]= str[3];
                aux[8]= str[4];
                aux[9]= str[5];
                for (String[] str2: old) {
                    if (str[0].equals(str2[0]) && str[1].equals(str2[1])) {

                        aux[2]= str2[2];
                        aux[3]= str2[3];
                        aux[4]= str2[4];
                        aux[5]= str2[5];

                        if (Long.parseLong(str[2]) > Long.parseLong(str2[2])) {
                            aux[10] = "warn";
                        } else {
                            for (int i = 3; i < 6; i++){
                                if (Float.parseFloat(str[i]) > Float.parseFloat(str2[i])) {
                                    aux[10] = "warn";
                                }
                            }
                        }
                        break;
                    }
                }
                combined.add(aux);
            }

            List<String[]> temp = new ArrayList<>();
            for (String [] str: old) {
                Iterator it = combined.iterator();
                while (it.hasNext()) {
                    String [] straux = (String [])it.next();
                    if (!str[0].equals(straux[0]) && !str[1].equals(straux[1])) {
                        String[] aux2 = new String[11];
                        aux2[0]= str[0];
                        aux2[1]= str[1];
                        aux2[2]= str[2];
                        aux2[3]= str[3];
                        aux2[4]= str[4];
                        aux2[5]= str[5];
                        aux2[6]= null;
                        aux2[7]= null;
                        aux2[8]= null;
                        aux2[9]= null;
                        aux2[10] = "deprecated";
                        temp.add(aux2.clone());
                    }
                }
            }
            combined.addAll(temp);

            return combined;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static void removeDuplicatedLines(List<String[]> csv) {

        List<String[]> read = new ArrayList<>();
        List<Integer> indexesToRemove = new ArrayList<>();
        int i = 0;
        boolean exists = false;
        for (String[] str: csv) {

            String [] aux = new String[2];
            aux[0] = str[0];
            aux[1] = str[1];

            Iterator it = read.iterator();
            while (it.hasNext()) {
                String [] straux = (String [])it.next();
                if (str[0].equals(straux[0]) && str[1].equals(straux[1]))
                    exists = true;
            }

            if (exists) {
                indexesToRemove.add(i);
            } else {
                read.add(aux.clone());
            }
            i++;
            exists = false;
        }

        Collections.sort(indexesToRemove, Collections.reverseOrder());
        for (int index : indexesToRemove) {
            csv.remove(index);
        }
    }

    public static void main(String[] args) {

       generateTargetreports();
    }
}