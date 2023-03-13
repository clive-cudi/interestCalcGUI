package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcFrame extends JFrame implements ActionListener {
    static final String DEFAULT_TITLE = "New Window";
    static final int DEFAULT_WIDTH = 420;
    static final int DEFAULT_HEIGHT = 400;
    private static CustomInput inputPrincipalAmount;
    private static CustomInput inputRate;
    private static CustomInput inputTime;
    private static CustomInput noOfTimesAnnually;
    private static CustomInput answerInput;
    private static JRadioButton radioBtnSimpleInterest;
    private static JRadioButton radioBtnCompoundInterest;
    private static String selectedCalculation;
    public static JButton btn;

    CalcFrame() {
        renderFrame(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT, true);
    }


    CalcFrame(int width, int height) {
        renderFrame(DEFAULT_TITLE, width, height, false);
    }

    CalcFrame(String title, int width, int height) {
        renderFrame(title, width, height, false);
    }

    CalcFrame (String title) {
        renderFrame(title, DEFAULT_WIDTH, DEFAULT_WIDTH, true);
    }

    public JFrame renderFrame(String title, int width, int height, boolean pack) {
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setSize(width, height);
        this.setResizable(false);

        // ******* //
        System.out.println("Hello world!");

        // initialize radio buttons
        ButtonGroup btnGrp = new ButtonGroup();
        radioBtnSimpleInterest = new JRadioButton("Simple Interest");
        radioBtnCompoundInterest = new JRadioButton("Compound Interest");
        radioBtnSimpleInterest.addActionListener(this);
        radioBtnCompoundInterest.addActionListener(this);
        btnGrp.add(radioBtnSimpleInterest);
        btnGrp.add(radioBtnCompoundInterest);

        // initialize answer input
        answerInput = new CustomInput();
        answerInput.setPreferredSize(new Dimension(400, 100));
        answerInput.setEditable(false);
        JLabel answerInputLabel = new JLabel("Answer: ");
        answerInputLabel.setPreferredSize(new Dimension(150, 40));



        // initialize submit button
        btn = new JButton("Calculate");
        btn.setSize(200, 100);
        btn.addActionListener((e) -> {
            handleSubmit();
        });

        // initialize form inputs
        CustomInput[] availableInputs = renderInputs();

        for (int i = 0; i < availableInputs.length; i++) {
            JPanel inputStripPanel = new JPanel();
            JLabel inputLabel = new JLabel(matchInputLabel(i));
            inputStripPanel.add(inputLabel);
            inputStripPanel.add(availableInputs[i]);
            this.add(inputStripPanel);
        }

        // render to frame
        this.add(radioBtnSimpleInterest);
        this.add(radioBtnCompoundInterest);
        this.add(answerInputLabel);
        this.add(answerInput);

        this.add(btn);

        this.setVisible(true);

        return this;
    }

    private static CustomInput[] renderInputs() {
        CustomInput[] inputs = {new CustomInput(), new CustomInput(), new CustomInput(), new CustomInput()};

        for (int i = 0; i < inputs.length; i++) {
            CustomInput input = inputs[i];

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
                case 3:
                    noOfTimesAnnually = input;
                    break;
            }
        }

        return inputs;
    }

    public static double calcSimpleInterest(int principalAM, int rate, int time) {
        return principalAM * (1 + (rate * time));
    }

    public static double calcCompoundInterest(int principalAmount, int rate, int time, int numberOfTimes) {
        return Math.pow(principalAmount * (1 + (rate/numberOfTimes)), (numberOfTimes*time));
    }

    public static void handleSubmit() {
        try {
            if (selectedCalculation == "simple" || selectedCalculation == "compound") {
                // continue with calculation
                System.out.println("CALC Mode: " + selectedCalculation);
                // confirm the input data types
                if (isInteger(inputPrincipalAmount.getText())) {
                    System.out.println(inputPrincipalAmount.getText());
                } else {
                    throw new Exception("Principal Amount should be an integer");
                }
                if (isInteger(inputRate.getText())) {
                    int rate = Integer.parseInt(inputRate.getText());
                    if (rate >= 0 && rate <=100) {
                        System.out.println("Input Rate: " + rate + "%");
                    } else {
                        throw new Exception("The Rate should be a percentage...Between 0 and 100");
                    }
                } else {
                    throw new Exception("Rate should be an integer");
                }
                if (isInteger(inputTime.getText())) {
                    int time = Integer.parseInt(inputTime.getText());
                    if (time > 0) {
                        System.out.println("Time in years: " + time);
                    } else {
                        throw new Exception("Time should be a non-zero, greater than zero Integer");
                    }
                } else {
                    throw new Exception("Invalid input Time (in years)");
                }
                if (selectedCalculation == "compound") {
                    if (isInteger(noOfTimesAnnually.getText())) {
                        int nt = Integer.parseInt(noOfTimesAnnually.getText());
                        // handle compound interest calculation;
                        int pAm = Integer.parseInt(inputPrincipalAmount.getText());
                        int rate = Integer.parseInt(inputPrincipalAmount.getText());
                        int time = Integer.parseInt(inputPrincipalAmount.getText());
                        int numberOfTimesPerPeriod = Integer.parseInt(noOfTimesAnnually.getText());
                        System.out.println("Calculating Compound Interest::");

                        double compoundInterest = calcCompoundInterest(pAm, rate, time, numberOfTimesPerPeriod);

                        answerInput.setText("" + compoundInterest);
                    } else {
                        throw new Exception("Number of Times Annually should be an integer");
                    }
                }
                if (selectedCalculation == "simple") {
                    // handle simple interest calculations;
                    int pAm = Integer.parseInt(inputPrincipalAmount.getText());
                    int rate = Integer.parseInt(inputPrincipalAmount.getText());
                    int time = Integer.parseInt(inputPrincipalAmount.getText());
                    System.out.println("Calculating Simple Interest::");
                    double simpleInterest = calcSimpleInterest(pAm, rate, time);

                    answerInput.setText("" + simpleInterest);
                }
            } else {
                throw new Exception("Please select a Calculation scope");
            }
        } catch (Exception e) {
            answerInput.setText(e.getMessage());
            answerInput.setForeground(Color.red);
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == radioBtnSimpleInterest) {
            selectedCalculation = "simple";
        } else if (actionEvent.getSource() == radioBtnCompoundInterest) {
            selectedCalculation = "compound";
        }
    }

    public static String matchInputLabel(int index) {
        switch (index) {
            case 0:
                return "Enter Principal Amount";
            case 1:
                return "Enter Rate (1 - 100)";
            case 2:
                return "Enter time (in years)";
            case 3:
                return "No. of times Annually";
            default:
                return "";
        }
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
