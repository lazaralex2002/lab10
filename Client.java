import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;
    private DataInputStream in = null;

    public Client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            System.out.println("Write a command!");
            System.out.println("If you write \"Stop\" command, you'll stop the current program!");

            input = new DataInputStream(System.in);
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        String line = "";
        String line2 = "";
        while (!line.equals("Stop"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }

            catch(IOException i)
            {
                System.out.println(i);
            }
            System.out.println("Server received the request");
        }
        System.out.println("Server Stopped");
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        Client client = new Client("localhost", 5000);
    }
}
