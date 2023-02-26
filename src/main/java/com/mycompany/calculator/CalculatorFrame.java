/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 *
 * @author aarya
 */
public class CalculatorFrame extends JFrame implements ActionListener {
    JTextField tf; 
    JButton[] numberButtons;
    List<JButton> operatorButtons;
    int size;
    String result;
    String operator;
   
    
    CalculatorFrame(){
        size = 500;
        this.setVisible(true);
        this.setSize(size,size);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Calculator");
        this.getContentPane().setBackground(Color.black);
        this.setResizable(false);
        
        this.setLayout(null);
        
        int buttonSize = 50;
        int margin = 10; // between buttons 
        int leftMargin = (size - ( 4 * (buttonSize  +  margin) ) )/2 ; // for grid centered
        int topMargin = 100; // for grid 

        
        this.addButtons(buttonSize, margin, leftMargin, topMargin);
        this.addTextField(leftMargin, 20, ( 4 * (buttonSize)  +  3*(margin) ),60); // width = width of button panel
        this.setButtonColor();
    }
    private void setButtonColor(){
        for(int i=0;i<10;i++){
            numberButtons[i].setBackground(Color.black);
            numberButtons[i].setForeground(Color.white);
            numberButtons[i].setFont(new Font("Consolas",Font.PLAIN,20));
          
        }
         for(int i=0;i<operatorButtons.size();i++){
            operatorButtons.get(i).setBackground(Color.black);
            operatorButtons.get(i).setForeground(Color.white);
            operatorButtons.get(i).setFont(new Font("Consolas",Font.PLAIN,20));

        }
    }
    
    private void addTextField(int leftMargin, int topMargin, int width, int height ){
        tf = new JTextField();
        this.add(tf);
        tf.setBounds(leftMargin, topMargin, width, height);
        tf.setFont(new Font("Consolas",Font.PLAIN,35));
        tf.setForeground(Color.WHITE);
        tf.setBackground(Color.BLACK);
        
    }
    private void addButtons(int buttonSize, int margin, int leftMargin, int topMargin){
        numberButtons = new JButton[10];
        operatorButtons = new ArrayList();
        
        //create operator buttons
        String operators = "/*+-=";
        for(int i=0;i<operators.length();i++){
            char currOperator = operators.charAt(i);
            JButton button = new JButton(currOperator+"");
            operatorButtons.add(button);
                    
        }
        // adding clear button 
        operatorButtons.add(new JButton("C"));
        
        
        int x = leftMargin ;
        int y= topMargin;
        
        int currentRowButtons = 0;
        
        
        
        // create numberButtons and add all buttons to Frame
        int currentRow = 0;
        for(int i=0;i<10;i++){
            
           JButton currentButton = new JButton(String.valueOf(i));
           currentButton.setBounds(x, y, buttonSize, buttonSize);
           currentButton.addActionListener(this);
           
           numberButtons[i] = currentButton;
            
            this.add(numberButtons[i]);
            
            currentRowButtons++;
            x+= buttonSize + margin;
            
            if(currentRowButtons==3 ){
                
                JButton operator = operatorButtons.get(currentRow);
                operator.setBounds(x, y, buttonSize, buttonSize);
                operator.addActionListener(this);
                
                
                this.add(operator);
                y+=buttonSize + margin; // set to y margin
                x=leftMargin; // reset to left margin 
                currentRowButtons = 0;
                currentRow++;
            }
            
//            if(i==8) x = leftMargin + buttonSize + margin; // center algin for 9
            if(i==9){
                int iter = currentRow;
              while(iter<operatorButtons.size()){
                JButton operator = operatorButtons.get(iter);
                operator.setBounds(x, y, buttonSize, buttonSize);
                operator.addActionListener(this);
                
                
                this.add(operator);
                x+= buttonSize + margin; // reset to left margin 
                iter++;
                  
              }  
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JButton buttonPressed = (JButton) e.getSource(); // getting the event source
      
        boolean pressedOperator = false;
        
        for(int i=0;i<operatorButtons.size();i++){
            // if pressed if operator 
            if(operatorButtons.get(i)==buttonPressed){
                if(operatorButtons.get(i).getText().equals("="))break;
                if(operatorButtons.get(i).getText().equals("C"))break;
                this.result = tf.getText();
                tf.setText("");
                this.operator = operatorButtons.get(i).getText();
                
                pressedOperator = true;
                break;
            }
        }
        if(buttonPressed.getText().equals("=")){ // handling equals 
            pressedOperator = true;
            if(this.operator!=null){
                
                int num1 = Integer.parseInt(result);
                int num2 = Integer.parseInt(tf.getText());
                
                if(this.operator.equals("+")) tf.setText(String.valueOf(num1+num2));
                if(this.operator.equals("-")) tf.setText(String.valueOf(num1-num2));
                
                if(this.operator.equals("/")) tf.setText(String.valueOf(num1/num2));
                if(this.operator.equals("*")) tf.setText(String.valueOf(num1*num2));
                
                this.operator = null;
            }
            
        }
        
        if(buttonPressed.getText().equals("C")){
            this.result = null;
            tf.setText("");
            pressedOperator = true;
        }

        if(!pressedOperator) tf.setText(tf.getText() + buttonPressed.getText()); // other keys besides operator 
    }
}
