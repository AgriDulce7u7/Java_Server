import java.io.*;
import java.net.*;
public class ServidorTCP {
public static void main(String[] args) {
int puerto = 5000;
try (ServerSocket serverSocket = new ServerSocket(puerto)) {
System.out.println("Servidor TCP escuchando en el puerto " + puerto + "...");
while (true) {
// Acepta la conexión entrante del cliente
Socket socketCliente = serverSocket.accept();
// Identificación de la dirección IP del cliente conectado
InetAddress direccionIPCliente = socketCliente.getInetAddress();
int puertoRemotoCliente = socketCliente.getPort();

System.out.println("\n----------------------------------------");
System.out.println("¡Cliente conectado!");
System.out.println("IP del cliente: " + direccionIPCliente.getHostAddress());
System.out.println("Puerto remoto del cliente: " + puertoRemotoCliente);
System.out.println("----------------------------------------");
// Configuración de flujos de entrada y salida
BufferedReader entrada = new BufferedReader(new
InputStreamReader(socketCliente.getInputStream()));
PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);
// Lectura del mensaje enviado por el cliente
String mensajeRecibido = entrada.readLine();
System.out.println("Mensaje original recibido: " + mensajeRecibido);
if (mensajeRecibido != null) {
// Proce    samiento de la cadena: Convertir a mayúsculas e invertir

String mensajeProcesado = new

StringBuilder(mensajeRecibido.toUpperCase()).reverse().toString();
System.out.println("Mensaje procesado a enviar: " + mensajeProcesado);
// Envío de la respuesta al cliente
salida.println(mensajeProcesado);

}
// Cierre de la conexión individual
socketCliente.close();
System.out.println("Conexión con el cliente cerrada.");
}
} catch (IOException e) {
e.printStackTrace();
}
}
}