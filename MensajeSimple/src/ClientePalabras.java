import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

public class ClientePalabras {
	public static void main(String[] args) {
		String host = "127.0.0.1";
		int puerto = 20000;

		try (Socket socket = new Socket(host, puerto);
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				Writer salida = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				Scanner teclado = new Scanner(System.in)) {

			while (true) {
				System.out.print("Introduce una palabra: ");
				String palabra = teclado.nextLine();
				

				// Envía la palabra terminada en salto de línea y vacía el buffer
				salida.write(palabra + "\n");
				salida.flush();

				String resp1 = entrada.readLine();
				String resp2 = entrada.readLine();
				
				

				System.out.println("Respuesta 1 -> " + resp1);
				System.out.println("Respuesta 2 -> " + resp2);
				
				if (palabra.equalsIgnoreCase("fin")) {
                    break;
                }
				System.out.println("Cliente finalizado");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}