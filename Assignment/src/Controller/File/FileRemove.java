package Controller.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRemove {
    public void removeCamp(String csvFilePath, String campName){
        List<String[]> rows = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                String [] colums = line.split(",");
                if(!line.contains(campName)){
                    rows.add(colums);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath))){
            for (String [] row : rows) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i<row.length; i++){
                    line.append(row[i]);
                    if(i<row.length-1){
                        line.append(",");
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
