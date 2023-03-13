package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    private static CustomInput inputPrincipalAmount;
    private static CustomInput inputRate;
    private static CustomInput inputTime;
    private static CustomInput answerInput;
    private String principal;
    private String rate;
    public static JButton btn;
    public static void main(String[] args) {
        System.out.println("Hello world!");

        CalcFrame frame = new CalcFrame("Interest Calculator");

        btn = new JButton("Submit");
        btn.setSize(100, 100);

        for (CustomInput input:
             renderInputs()) {
            frame.add(input);
            input.addActionListener(e -> {
                System.out.println(input.getText());
                System.out.println(e.getSource());
            });
        }
        CustomInput customInput = new CustomInput();


        frame.add(customInput);
        frame.add(btn);

        frame.setVisible(true);
    }

    private static CustomInput[] renderInputs() {
        CustomInput[] inputs = {new CustomInput(), new CustomInput(), new CustomInput()};

        for (int i = 0; i < inputs.length; i++) {
            CustomInput input = inputs[i];
//            input.addActionListener(e -> {
//                System.out.println(input.getText());
//            });

            switch (i) {
                case 0:
                    inputPrincipalAmount = input;
                    break;
                case 1:
                    inputRate = input;
                    break;
                case 2:
                    inputTime = input;
                    break;
            }
        }

        return inputs;
    }

    public static double calcSimple(int principalAM, int rate) {

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}