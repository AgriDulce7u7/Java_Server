import java.io.*;
import java.net.*;
public class ClienteTCP {
public static void main(String[] args) {
String hostServidor = "localhost";
int puertoServidor = 5000;
try (Socket socket = new Socket(hostServidor, puertoServidor)) {
// Identificación del puerto del cliente en el lado cliente
int puertoLocalCliente = socket.getLocalPort();
InetAddress ipLocalCliente = socket.getLocalAddress();
System.out.println("Conectado al servidor TCP.");
System.out.println("IP Local (Cliente): " + ipLocalCliente.getHostAddress());
System.out.println("Puerto Local (Cliente): " + puertoLocalCliente);
// Flujos de comunicación
PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
BufferedReader entrada = new BufferedReader(new
InputStreamReader(socket.getInputStream()));

// Cadena a enviar
String cadenaEnviar = "Sistemas Distribuidos 2026";
System.out.println("\nCadena enviada al servidor: " + cadenaEnviar);
// Envío del texto
salida.println(cadenaEnviar);
// Recepción del echo procesado
String respuesta = entrada.readLine();
System.out.println("Respuesta procesada recibida del servidor: " + respuesta);
} catch (IOException e) {
e.printStackTrace();
}
}
}