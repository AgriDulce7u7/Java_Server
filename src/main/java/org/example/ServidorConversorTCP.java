package org.example;

import java.io.*;
import java.net.*;

public class ServidorConversorTCP {
    public static void main(String[] args) {
        int puerto = 7000;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor de Conversión TCP escuchando en el puerto " + puerto +
                    "...");
            while (true) {
                Socket socketCliente = serverSocket.accept();
                System.out.println("\nCliente conectado desde: " +
                        socketCliente.getInetAddress().getHostAddress());
                BufferedReader entrada = new BufferedReader(new
                        InputStreamReader(socketCliente.getInputStream(), "UTF-8"));
                PrintWriter salida = new PrintWriter(new

                        OutputStreamWriter(socketCliente.getOutputStream(), "UTF-8"), true);
                String solicitud = entrada.readLine();
                System.out.println("Solicitud recibida: " + solicitud);
                if (solicitud != null) {
                    String respuesta = procesarSolicitud(solicitud);
                    System.out.println("Respuesta enviada: " + respuesta);
                    salida.println(respuesta);

                }
                socketCliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String procesarSolicitud(String solicitud) {
        try {
            String[] partes = solicitud.split(";");
            int op = Integer.parseInt(partes[0]);
            switch (op) {
                case 1: { // Decimal a Binario con rellenado de bits
                    long dec = Long.parseLong(partes[1]);
                    int bits = Integer.parseInt(partes[2]);
                    String bin = Long.toBinaryString(dec);
                    return rellenarCerosIzquierda(bin, bits);

                }
                case 2: { // Binario a Decimal
                    String bin = partes[1];

                    long dec = Long.parseLong(bin, 2);
                    return String.valueOf(dec);

                }
                case 3: { // Decimal a Hexadecimal con rellenado de dígitos
                    long dec = Long.parseLong(partes[1]);
                    int digitos = Integer.parseInt(partes[2]);
                    String hex = Long.toHexString(dec).toUpperCase();
                    return rellenarCerosIzquierda(hex, digitos);

                }
                case 4: { // Hexadecimal a Decimal
                    String hex = partes[1];

                    long dec = Long.parseLong(hex, 16);
                    return String.valueOf(dec);

                }
                case 5: { // Binario a Hexadecimal
                    String bin = partes[1];

                    long dec = Long.parseLong(bin, 2);
                    return Long.toHexString(dec).toUpperCase();

                }
                case 6: { // Hexadecimal a Binario
                    String hex = partes[1];

                    long dec = Long.parseLong(hex, 16);
                    return Long.toBinaryString(dec);

                }
                default:
                    return "ERROR: Operacion invalida";
            }
        } catch (Exception e) {
            return "ERROR: Formato de solicitud invalido";
        }
    }

    private static String rellenarCerosIzquierda(String texto, int longitudDeseada) {
        if (texto.length() >= longitudDeseada) {
            return texto;
        }
        StringBuilder sb = new StringBuilder();

        while (sb.length() < longitudDeseada - texto.length()) {
            sb.append('0');
        }
        sb.append(texto);
        return sb.toString();
    }
}