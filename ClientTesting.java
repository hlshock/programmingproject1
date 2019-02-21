/**
 * After running the ServerTesting in one terminal window, in a second terminal window
 * run this test program
 */
public class ClientTesting {
  public static void main(String[] args) {
    Check1();
    System.out.println("");
    Check2();
    System.out.println("");
    Check3();
    System.out.println("");
    Check4();
  }

  public static void Check1() {
    Client client = new Client("localhost", 4445);
    System.out.println("CLIENT:");
    System.out.println("---------- Checkpoint 1: Basic Communication -----------------");
    if(client.sendMessage("pineapple") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client.sendMessage("banana") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client.sendMessage("orange") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }

    if(client.sendMessage("end") == true)
    {
      System.out.println("Message 3: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 3: " + "FAILURE");
    }
    client.close();
    System.out.println("CHECKPOINT 1 TEST DONE. ");
  }

  public static void Check2() {
    System.out.println("--------- Checkpoint 2: Reliable Communication Part 1 ------------");
    //client connects to socket of network
    Client client2 = new Client("localhost", 4444);

    if(client2.sendMessage("blueberry") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client2.sendMessage("strawberry") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client2.sendMessage("raspberry") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }

    if(client2.sendMessage("end") == true)
    {
      System.out.println("Message 3: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 3: " + "FAILURE");
    }
    client2.close();
    System.out.println("CHECKPOINT 2 TEST DONE. ");
  }
  public static void Check3() {
    System.out.println("--------- Checkpoint 3: Reliable Communication Part 2 ------------");
    //client connects to socket of network
    Client client3 = new Client("localhost", 4444);

    if(client3.sendMessage("mango") == true)
    {
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if(client3.sendMessage("grapes") == true)
    {
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if(client3.sendMessage("kiwi") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }

    if(client3.sendMessage("end") == true)
    {
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }
    client3.close();
    System.out.println("CHECKPOINT 3 TEST DONE. ");
  }

  public static void Check4() {
    System.out.println("--------- Checkpoint 4: Large	message	transfer ------------");
    Client client4 = new Client("localhost", 4444);
    if (client4.sendMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent volutpat pellentesque laoreet. Quisque nulla purus, ultrices tristique justo at, porttitor mollis mauris. Sed mollis hendrerit dui eget cursus. Integer semper semper felis, id blandit diam tristique vitae. Nulla congue a elit ac convallis. Nam eu massa ac magna vulputate aliquet. Aliquam in tortor non ante varius rutrum. Nulla pretium massa dolor, eu rhoncus tellus volutpat vehicula. Ut ac viverra lectus, eu blandit diam. Donec a mi nullam.") == true){
      System.out.println("Message 0: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 0: " + "FAILURE");
    }

    if (client4.sendMessage("He crossed toward the pharmacy at the corner he involuntarily turned his head because of a burst of light that had ricocheted from his temple, and saw, with that quick smile with which we greet a rainbow or a rose, a blindingly white parallelogram of sky being unloaded from the van—a dresser with mirrors across which, as across a cinema screen, passed a flawlessly clear reflection of boughs sliding and swaying not arboreally, but with a human vacillation, produced by the nature of those who were carrying the sky, these boughs, the gliding façade.") == true){
      System.out.println("Message 1: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 1: " + "FAILURE");
    }

    if (client4.sendMessage("In the loveliest town of all, where the houses were white and high and the elms trees were green and higher than the houses, where the front yards were wide and pleasant and the back yards were bushy and worth finding out about, where the streets sloped down to the stream and the stream flowed quietly under the bridge, where the lawns ended in orchards and the orchards ended in fields and the fields ended in pastures and the pastures climbed the hill and disappeared over the top toward the wonderful wide sky, in this loveliest of all towns Stuart stopped to get a drink of sarsaparilla.") == true){
      System.out.println("Message 2: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 2: " + "FAILURE");
    }

    if (client4.sendMessage("While the men made bullets and the women lint, while a large saucepan of melted brass and lead, destined to the bullet-mould smoked over a glowing brazier, while the sentinels watched, weapon in hand, on the barricade, while Enjolras, whom it was impossible to divert, kept an eye on the sentinels, Combeferre, Courfeyrac, Jean Prouvaire, Feuilly, Bossuet, Joly, Bahorel, and some others, sought each other out and united as in the most peaceful of days of their conversations in their student life, and, in one corner of this wine-shop which had been converted into a casement, a couple of paces distant from the redoubt which they had built, with their carbines loaded and primed resting against the backs of their chairs, these fine young fellows, so close to a supreme hour, began to recite love verses.") == true){
      System.out.println("Message 3: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 3: " + "FAILURE");
    }

    if (client4.sendMessage("end") == true){
      System.out.println("Message 4: " + "SUCCESS");
    }
    else
    {
      System.out.println("Message 4: " + "FAILURE");
    }
  }
}
