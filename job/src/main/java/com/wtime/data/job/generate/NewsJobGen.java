package com.wtime.data.job.generate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class NewsJobGen {
	
	public static void main(String[] args){
		String clawdir="D:\\Java\\workspace\\invest-data\\claw\\src\\main\\java\\com\\wtime\\data\\news";
		String jobdir="D:\\Java\\workspace\\invest-data\\job\\src\\main\\java\\com\\wtime\\data\\job\\news";
		String modelpath="D:\\Java\\workspace\\invest-data\\job\\src\\main\\resources\\NewsJobModel.txt";
		// get file list where the path has   
        File file = new File(clawdir);   
        // get the folder list   
        File[] array = file.listFiles();  
          
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){   
                String filename=array[i].getName();
//                if(filename.indexOf(arg0))
                String news=filename.replace(".java", "");
                FileReader fr=null;
                
                BufferedReader br=null;
                BufferedWriter bw=null;
        		try {
        			bw = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (jobdir+"\\\\"+news+"Job.java"), "UTF-8"));
        		} catch (UnsupportedEncodingException e2) {
        			// TODO Auto-generated catch block
        			e2.printStackTrace();
        		} catch (FileNotFoundException e2) {
        			// TODO Auto-generated catch block
        			e2.printStackTrace();
        		}
                try {
					fr=new FileReader(modelpath);
					br=new BufferedReader(fr);
					try{
						String line=br.readLine();
						while(line!=null){
							line=line.replace("{News}", news)+"\r\n";
							bw.write(line);
							line=br.readLine();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                try {
                	br.close();
        			bw.flush ();
        			bw.close ();
        		} catch (IOException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                
            }
        }    
	}
}
