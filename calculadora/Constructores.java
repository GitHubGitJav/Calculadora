package calculadora;

import java.awt.*;
import javax.swing.*;

public class Constructores extends JFrame {
    private final JTextField pantalla;
    private double numeroActual = 0;
    private String operacionActual = "";
    private boolean puntoDecimal = false;

    public Constructores() {
        super("Calculadora");
        setSize(400, 450);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container objContenedor = getContentPane();
        objContenedor.setLayout(new GridBagLayout());

        GridBagConstraints objPosicionPantalla = new GridBagConstraints();
        objPosicionPantalla.gridx = 0;
        objPosicionPantalla.gridy = 0;
        objPosicionPantalla.gridwidth = 4;
        objPosicionPantalla.fill = GridBagConstraints.BOTH;
        objPosicionPantalla.weightx = 1.0;
        objPosicionPantalla.weighty = 0.5;
        objPosicionPantalla.insets = new Insets(5, 5, 5, 5);

        pantalla = new JTextField();
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        objContenedor.add(pantalla, objPosicionPantalla);

        JButton objBotonDeResultado = new JButton("Resultado");
        objBotonDeResultado.addActionListener(e -> {
            calcularResultado();
        });
        
        GridBagConstraints objPosicionBotonDeResultado = new GridBagConstraints();
        objPosicionBotonDeResultado.gridx = 0;
        objPosicionBotonDeResultado.gridy = 1;
        objPosicionBotonDeResultado.gridwidth = 4;
        objPosicionBotonDeResultado.fill = GridBagConstraints.BOTH;
        objPosicionBotonDeResultado.weightx = 1.0;
        objPosicionBotonDeResultado.weighty = 0.25;
        objPosicionBotonDeResultado.insets = new Insets(5, 5, 5, 5);
        objContenedor.add(objBotonDeResultado, objPosicionBotonDeResultado);

        GridBagConstraints objPosicionBotonesCalculadora = new GridBagConstraints();
        objPosicionBotonesCalculadora.fill = GridBagConstraints.BOTH;
        objPosicionBotonesCalculadora.weightx = 0.25;
        objPosicionBotonesCalculadora.weighty = 0.25;
        objPosicionBotonesCalculadora.insets = new Insets(5, 5, 5, 5);

        String[][] arregloEtiquetasBotones = {
            {"7", "8", "9", "÷"},
            {"4", "5", "6", "x"},
            {"1", "2", "3", "-"},
            {"C", "0", ".", "+"}
        };

        for (int filas = 0; filas < 4; filas++) {
            for (int columnas = 0; columnas < 4; columnas++) {
                JButton objBotonCalculadora = new JButton(arregloEtiquetasBotones[filas][columnas]);
                objPosicionBotonesCalculadora.gridx = columnas;
                objPosicionBotonesCalculadora.gridy = filas + 2;

                objBotonCalculadora.addActionListener(e -> {
                    String botonPresionado = e.getActionCommand();
                    if ("0123456789".contains(botonPresionado)) {
                        pantalla.setText(pantalla.getText() + botonPresionado);
                    } else if ("+-x÷".contains(botonPresionado)) {
                        operacionActual = botonPresionado;
                        numeroActual = Double.parseDouble(pantalla.getText());
                        pantalla.setText("");
                        puntoDecimal = false;
                    } else if (".".equals(botonPresionado)) {
                if (!puntoDecimal) {
                    pantalla.setText(pantalla.getText() + botonPresionado);
                    puntoDecimal = true;
                }
                    } else if ("C".equals(botonPresionado)) {
                        pantalla.setText("");
                        numeroActual = 0;
                    } else if ("Resultado".equals(botonPresionado)) {
                        calcularResultado();
                    }
                });

                objContenedor.add(objBotonCalculadora, objPosicionBotonesCalculadora);
            }
        }
    }

    private void calcularResultado() {
        double numero2 = Double.parseDouble(pantalla.getText());
        double resultado = 0;
        switch (operacionActual) {
            case "+":
                resultado = numeroActual + numero2;
                break;
            case "-":
                resultado = numeroActual - numero2;
                break;
            case "x":
                resultado = numeroActual * numero2;
                break;
            case "÷":
                resultado = numeroActual / numero2;
                break;
        }
        pantalla.setText(Double.toString(resultado));
        numeroActual = resultado; 
        operacionActual = ""; 
    }
}