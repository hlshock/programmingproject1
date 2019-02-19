/**
 * In terminal window, run this program, which starts the server running,
 * then see ClientTesting class
 */
public class ServerTesting {

  public static void main(String[] args) {
    System.out.println("Server Checkpoint 1:");
    Server server = new Server(4445);
    server.run();
    System.out.println("\nServer Checkpoint 2:");
    Server server2 = new Server(4445);
    server2.run();
    System.out.println("\nServer Checkpoint 3:");
    Server server3 = new Server(4445);
    server3.run();
  }
}
