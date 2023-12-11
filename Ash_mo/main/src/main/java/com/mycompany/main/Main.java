/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;
import java.util.*;

/**
 *
 * @author Ahmed Shaaban
 */
public class Main {

    public static void main(String[] args) throws Exception {
        FileStorage file = new FileStorage();
        Scanner input = new Scanner(System.in);
        try{
            file.createTable("request", "ID","emp_id", "massage", "send date", "statu");
        }
        catch(Exception ex){
            System.out.println("cannot create file..." + ex.getMessage());
        }
        try{
            Request req = new Request();
            System.out.println("Write your request");
            String massage = input.nextLine();
            req.send(5, massage);
            req.send(6, massage);
            req.send(7, massage);
            for(String[] row :req.allRequest()){
                System.out.println("Employee: " + row[1] + "\t\t\tDate: " + row[3]);
                System.out.println(row[2]);
                System.out.println("Enter : approve or refuse or anythig to skip");
                System.out.print("Enter: ");
                String statu = input.next();
                req.respondRequest(row[0], statu);
                System.out.println("\t\t---------------------");
            }
            int count=0;
            for(String[] row :req.seeRespond(5)){
                System.out.println("Request: " + (++count) + "\t\t\tDate: " + row[3]);
                System.out.println(row[2]);
                System.out.println("Request is " + row[4]);
                System.out.println("\t\t---------------------");
            }
        }
        catch(Exception ex){
            System.out.println( ex.getMessage());
        }
        // Add in login constractor
        Request.filter();
    }
    
}
