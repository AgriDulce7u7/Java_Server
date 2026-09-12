package org.example;

import java.net.*;
public class ClienteUDP {
    public static void main(String[] args) {
        String hostServidor = "localhost";
        int puertoServidor = 6000;
        try (DatagramSocket socketUDP = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName(hostServidor);
            String mensaje = "Hola servidor UDP";
            byte[] buferSalida = mensaje.getBytes();
// Creación del paquete de salida
            DatagramPacket paqueteEnvio = new DatagramPacket(
                    buferSalida,
                    buferSalida.length,
                    direccionServidor,
                    puertoServidor
            );
            System.out.println("Enviando mensaje UDP...");
            socketUDP.send(paqueteEnvio);
// Preparación del paquete para recibir respuesta
            byte[] buferEntrada = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(buferEntrada,
                    buferEntrada.length);
            socketUDP.receive(paqueteRespuesta);
            String respuesta = new String(paqueteRespuesta.getData(), 0,
                    paqueteRespuesta.getLength());
            System.out.println("Respuesta del Servidor UDP: " + respuesta);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
