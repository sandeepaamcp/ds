package com.ds.main.service;

import com.ds.main.SpringBootRestApplication;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Random;

/**
 * @desc file service to handle the serving files
 */
@Service
public class FileService {
    Random randomNum = new Random();
    String files[] = new String[20];
    String[] servingFiles;

    public FileService() throws IOException {
        File file = ResourceUtils.getFile("classpath:static/File_Names.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int counter=0;
        while ((st = br.readLine()) != null){
            files[counter] = st;
            counter++;
        }
        br.close();
        setServingFiles();
    }

    /**
     * @desc get a single file
     * @param name
     * @return
     */
    public String getFile(String name){
        for(int i=0; i<servingFiles.length; i++){
            if(servingFiles[i].equals(name)){
                return servingFiles[i];
            }
        }
        return null;
    }

    /**
     * @desc get all files
     * @return
     * @throws IOException
     */
    public String[] getAll() throws IOException {
        return files;
    }

    /**
     * @desc get all serving files
     * @return
     */
    public String[] getAllServingFiles(){
        return servingFiles;
    }

    /**
     * @desc set the serving files
     */
    public void setServingFiles(){
        int rand = 3 + randomNum.nextInt(6-3);
        servingFiles = new String[rand];
        for(int i=0; i<rand; i++) {
            int index = randomNum.nextInt(20);
            servingFiles[i] = files[index];
        }
        SpringBootRestApplication.servingFiles = this.servingFiles;
    }
}
