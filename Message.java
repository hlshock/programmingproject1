/**
 * Represents a message of up to 255 bytes in size alomng with other information
 */
public class Message {
  String messageContents;
  int sequenceCounter;

  //sending - need to create packet to be sent
  public Message(String message) {
    messageContents = message;
  }

  //receiving - recieved packet, need to "read" contents
  public Message(byte[] messageBytes) {

  }

  public int getSequenceCounter() {
    return sequenceCounter;
  }

  public void setSequenceCounter(int counter) {
    sequenceCounter = counter;
  }

  public String getMessageContents() {
    return messageContents;
  }

  public void setSequenceCounter(String message) {
    messageContents = message;
  }

  /**
   * @return the byte array representation of this Message object
   */
  public byte[] getBytes() {
    byte[] contents = messageContents.getBytes();
  }


}
