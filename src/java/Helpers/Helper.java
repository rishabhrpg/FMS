package Helpers;


import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marvel
 */
public class Helper {
    public static boolean validateSession(HttpSession session){        
         return true;
    }
    public static boolean validateAdmin(HttpSession session){
        
        try{
            String type = session.getAttribute("type").toString();
            System.out.println(" iska type : "+session.getAttribute("type"));
        }catch(Exception e){
            return false;
        }
                
        System.out.println("getting tye from session : "+session.getAttribute("type"));
        if(session.getAttribute("type").equals("admin")){
            System.out.println("returning true");
            return true;
        }
        else{
            System.out.println("returning false");
            return false;
        }
    }
    public static boolean validateTrainer(HttpSession session){
        try{
            String type = session.getAttribute("type").toString();           
        }catch(Exception e){
            return false;
        }
                
        System.out.println("getting type from session : "+session.getAttribute("type"));
        if(session.getAttribute("type").equals("manager")){
            System.out.println("returning true");
            return true;
        }
        else{
            System.out.println("returning false");
            return false;
        }  
    }

    public static boolean validateManager(HttpSession session) {
        try{
            String type = session.getAttribute("type").toString();           
        }catch(Exception e){
            return false;
        }                
        System.out.println("getting type from session : "+session.getAttribute("type"));
        if(session.getAttribute("type").equals("manager")){
            System.out.println("returning true");
            return true;
        }
        else{
            System.out.println("returning false");
            return false;
        }
    }
}