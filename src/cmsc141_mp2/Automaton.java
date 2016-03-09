/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc141_mp2;

/**
 *
 * @author parfait
 */
public class Automaton {
    private String currentState;
    private String nextState;
    private String input;
    
    Automaton(){
        this.currentState = "";
        this.input = "";
    }
    
    Automaton(String current, String input){
        this.currentState = current;
        this.input = input;
    }
    
    public String cross_with(String input){
        String[] str = this.currentState.split("_");
        String west = str[0];
        String east;
        if(str.length>1)
            east = str[1];
        else
            east = "";
        
        StringBuilder sb = new StringBuilder();
        String state;
        String[] temp;
        if(east.contains(input)){
            state = str_manipulate(east, west, input);
            temp = state.split(":");
            sb.append(temp[1]).append("_").append(temp[0]);
        }
        else if(west.contains(input)){
            state = str_manipulate(west, east, input);
            temp = state.split(":");
            sb.append(temp[0]).append("_").append(temp[1]);
        }
        else if (this.currentState.contains(input) == false){
            if(east.contains("F")){
                state = str_manipulate(east, west, input);
                temp = state.split(":");
                sb.append(temp[1]).append("_").append(temp[0]);
            }
            else{
                state = str_manipulate(west, east, "F");
                temp = state.split(":");
                sb.append(temp[0]).append("_").append(temp[1]);
            }
        }
        this.nextState = sb.toString();
        
        return this.nextState;
    }
    
    public String str_manipulate(String from, String to, String input){
        char[] rb = from.toCharArray();
        char[] c = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < rb.length; i++){
            if(rb[i] == c[0] && c[0] != 'F')
                to = to + input;
            else if(rb[i] == 'F')
                to = to + "F";
            else
                sb.append(rb[i]);
        }
        sb.append(":").append(to);
        
        return sb.toString();
    }
    
    public String getCurrentState() {
        return currentState;
    }

    public String getNextState() {
        return nextState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
