import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
  private Socket socket;

  public ServerThread(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try {
      InputStream input = socket.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));

      OutputStream output = socket.getOutputStream();
      PrintWriter writer = new PrintWriter(output, true);


            /*String text;

            do {
                text = reader.readLine();
                String reverseText = new StringBuilder(text).reverse().toString();
                writer.println("Server: " + reverseText);

            } while (!text.equals("bye"));*/
      String text;
      StringBuilder inString = new StringBuilder();
      System.out.println("Reading");
      while (reader.ready()) {
        text = reader.readLine();
        inString.append(text).append("\n");
        System.out.println(text);
      }
      System.out.println("Done!");
      writer.print("Responce:\n" + inString);
      writer.flush();
      writer.close();
//      System.out.println(inString);
      socket.close();
    } catch (IOException ex) {
      System.out.println("Server exception: " + ex.getMessage());
      ex.printStackTrace();
    }
  }
}
