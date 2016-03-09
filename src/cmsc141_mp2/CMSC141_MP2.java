/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc141_mp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author parfait
 */
public class CMSC141_MP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String state = "FLCR_"; //Initial state
        Automaton automaton = new Automaton();
        
        String fileName = "mp2.in";
        String[] textData = open_file(fileName);    //Holds the text data the file contains.
//        System.out.println("Current state: " + state);
        int ctr = 0;
        for (String line : textData){   //Iterates through the lines in the file
            char [] array = line.toCharArray();
            state = "FLCR_";
            automaton.setCurrentState(state);   //Sets the current state of the Automaton class
            
            for(char c : array){    //Iterates though all the characters the line has then executes each move
                state = automaton.cross_with(String.valueOf(c));
                automaton.setCurrentState(state);
                System.out.println("Input: " + c + " Current state: " + state);
                if(state_validate(state) == false){ //If move is invalid
                    System.out.println("NG");
                    break;
                }
            }
            if(state_validate(state) && state.startsWith("_")){ //If move is valid
                System.out.println(ctr + ": OK");
                ctr++;
            }
        }
    }
    
    public static String[] open_file(String fileName) throws FileNotFoundException, IOException{
        /*
        *Opens a file and extract its contents.
        */
        FileReader fr = new FileReader(fileName);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = readLines(fileName);
        String[] textData = new String[numberOfLines];
        
        for(int i = 0; i < numberOfLines; i++){
            textData[i] = textReader.readLine();
        }
        textReader.close();
        return textData;
    }
    
    public static int readLines(String fileName) throws FileNotFoundException, IOException{
        /*
        *Counts the total number of lines the file has.
        */
        FileReader fr = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fr);
        
        String aLine;
        int numberOfLines = 0;
        
        while((aLine = bf.readLine()) != null)
            numberOfLines++;
        bf.close();
        
        return numberOfLines;
    }
    
    public static boolean state_validate(String state){
        /*
        *Checks if the move's resulting state is valid or not.
        */
        String[] str = state.split("_");
        for (String s : str) {
            if (s.equals("LR") || s.equals("RC") || s.equals("RL") || s.equals("CR") || s.equals("LRC") 
                    || s.equals("LCR") || s.equals("RLC") || s.equals("RCL") || s.equals("CLR") || s.equals("CRL")){
                return false;
            }
        }
        return true;
    }
}
