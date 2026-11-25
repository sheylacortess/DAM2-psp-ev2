import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorPalabras {
    public static void main(String[] args) {
        int puerto = 20000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            // Espera a que se conecte un cliente
            try (Socket conexion = servidor.accept();
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
                 Writer salida = new OutputStreamWriter(conexion.getOutputStream(), "UTF-8")) {

                System.out.println("Cliente conectado.");

                // Lee una palabra enviada por el cliente
                String palabra = entrada.readLine();
                if (palabra != null) {
                    System.out.println("Recibida del cliente: " + palabra);

                    int numLetras = palabra.length();
                    String mayusculas = palabra.toUpperCase();

                    // Primer mensaje: número de letras
                    salida.write("Numero de letras: " + numLetras + "\n");
                    salida.flush();

                    // Segundo mensaje: palabra en mayúsculas
                    salida.write("Mayusculas: " + mayusculas + "\n");
                    salida.flush();
                }

                // TODO (siguiente paso): calcular longitud y mayúsculas, y enviar DOS líneas con salida.write(... + "\n") + salida.flush()
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}