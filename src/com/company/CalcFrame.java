package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcFrame extends JFrame implements ActionListener {
    static final String DEFAULT_TITLE = "New Window";
    static final int DEFAULT_WIDTH = 400;
    static final int DEFAULT_HEIGHT = 400;

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

        return this;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {}
}
