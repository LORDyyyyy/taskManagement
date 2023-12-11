/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import java.time.LocalDate;
import java.time.Duration;

/**
 *
 * @author Ahmed Shaaban
 */
public class Request {
    
    private FileStorage file = new FileStorage();
    private Helpers help = new Helpers();
    
    /**
     * employee send request
     * 
     * @param emp_id - employee id who send request
     * @param massage - request who wants to be send
     * @return 0 if success
     */
    public int send (int emp_id, String massage) throws Exception{
        int Id = file.getNextID("request");
        LocalDate time = LocalDate.now();
        try{
            file.add("request", help.paramsToArr(Id,emp_id,massage,time,"Under Review"));
            return 0;
        }
        catch (Exception ex){
            throw new Exception("Request not added..."+ex.getMessage());
        }
    }
    
    
    /**
     * leader see all request
     * @return all request in 2D array on success,or massage in index[0][0]
     */
    public String[][] allRequest (){
        String[][] all = file.read("request");
        if (help.isEmptyTable(all)){
            all[0][0] = "There are no request";
            return all;
        }
        else{
            int counter=0;
            String[][] requests = new String[all.length][all[0].length];
            for(String row[] : all){
                if (row[4].equals("Under Review")){
                    int count=0;
                    for(String item :row){
                        requests[counter][count] = item;
                        count++;
                    }
                }
                counter++;
            }
            return requests;
        }
    }
    
    
/** 
    * leader respond to a request
    * @param Id - request id 
    * @param respond - leader respond on request 
    * @return 0 on success
    */
    public int respondRequest (String Id, String respond) throws Exception{
        if(!respond.equals("approved") || !respond.equals("refused"))
            return 0;
        else{
            try{
                file.update("request", help.intToArr(0), help.paramsToArr(Id)
                        , help.intToArr(4), help.paramsToArr(respond));
            }
            catch(Exception ex){
                throw new Exception ("Updata not set..."+ex.getMessage());
            }
            return 0;
        }
    }
    
    
/** 
    * employee see respond to his request
    * @param emp_id - employee id we will search for him
    * @return 2D array on success ,or massage in index [0][0] 
    */
    public String[][] seeRespond (int emp_id) throws Exception{
        String[][] see = file.read("request", help.intToArr(1), help.paramsToArr(emp_id));
        if (see.length > 0)
            return see;
        else{
            see[0][0] = "You not have request";
            return see;
        }
    }

    //Call in login constractor
    static public void filter () throws Exception{
        FileStorage file = new FileStorage();
        Helpers help = new Helpers();
        String[][] all = file.read("request");
        LocalDate now = LocalDate.now();
        for(String[] row :all){
            LocalDate sendTime = LocalDate.parse(row[3]);
            Duration diff = Duration.between(now.atStartOfDay(), sendTime.atStartOfDay());
            long days = diff.toDays();
            if (days > 8){
                try{
                    file.delete("request", help.intToArr(0), help.paramsToArr(row[0]));
                }
                catch(Exception ex){
                    throw new Exception ("Cannot delet row from file request..." + ex.getMessage());
                }
            }
        }
    } 
}
