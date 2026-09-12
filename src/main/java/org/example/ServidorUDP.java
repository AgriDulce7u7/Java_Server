package org.example;

import java.net.*;
public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 6000;
        try (DatagramSocket socketUDP = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP escuchando en el puerto " + puerto +
                    "...");
            byte[] buferEntrada = new byte[1024];
            while (true) {
// Preparación del paquete de recepción
                DatagramPacket paqueteRecibido = new DatagramPacket(buferEntrada,
                        buferEntrada.length);
                socketUDP.receive(paqueteRecibido); // Espera por datos
                String mensaje = new String(paqueteRecibido.getData(), 0,
                        paqueteRecibido.getLength());
                InetAddress ipCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                System.out.println("\nMensaje UDP recibido de " +
                        ipCliente.getHostAddress() + ":" + puertoCliente);
                System.out.println("Contenido: " + mensaje);
// Procesamiento (Eco simple o modificación)
                String respuesta = "[ECHO UDP] " + mensaje;

                byte[] buferSalida = respuesta.getBytes();
// Construcción y envío del paquete de respuesta
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        buferSalida,
                        buferSalida.length,
                        ipCliente,
                        puertoCliente

                );
                socketUDP.send(paqueteRespuesta);
                System.out.println("Respuesta UDP enviada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
