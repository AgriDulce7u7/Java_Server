package org.example;

import java.io.*;
import java.net.*;

public class ClienteConversorTCP {
    public static void main(String[] args) {
        String hostServidor = "localhost";
        int puertoServidor = 7000;
// Ejemplos de pruebas enviando solicitudes formateadas
        String[] pruebas = {
                "1;25;8", // Decimal 25 a Binario (8 bits) -> 00011001
                "2;00011001", // Binario a Decimal -> 25
                "3;255;4", // Decimal 255 a Hexadecimal (4 digitos) -> 00FF
                "4;00FF", // Hexadecimal a Decimal -> 255
                "5;11111111", // Binario a Hexadecimal -> FF
                "6;FF" // Hexadecimal a Binario -> 11111111
        };
        for (String solicitud : pruebas) {
            try (Socket socket = new Socket(hostServidor, puertoServidor)) {
                PrintWriter salida = new PrintWriter(new
                        OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                BufferedReader entrada = new BufferedReader(new
                        InputStreamReader(socket.getInputStream(), "UTF-8"));
                System.out.println("\n----------------------------------------");
                System.out.println("Enviando solicitud: " + solicitud);
                salida.println(solicitud); // Agrega automaticamente \n
                String respuesta = entrada.readLine();
                System.out.println("Respuesta recibida: " + respuesta);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}