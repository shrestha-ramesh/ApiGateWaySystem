package com.apigateway;

public class Test {
    public static void main(String[] args) {
        boolean isFunded = false;
        boolean isPaid = false;
        boolean isReceived = false;
        boolean isWell = false;
        boolean isFound = false;
        String s = "rama";
        if(s.equals("shyam")){
            isReceived = true;
        }else if(s.equals("hari")){
            isFound = true;
        }else if(s.equals("koli")) {
            isFound = true;
        }else if(s.equals("virat")) {
            isFound = true;
        }else  if(s.equals("virat")){
            isPaid = true;
        }
    }
}
