import java.util.*;
/**
 * Represents a message:
 * first 255 bytes = string contents
 * next 1 byte = message counter
 */
public class Message {
  String messageContents;
  int sequenceCounter;

  //sending - need to create packet to be sent
  public Message(String message, int count) {
    messageContents = message;
    sequenceCounter = count;
  }

  //receiving - recieved packet, need to "read" contents
  public Message(byte[] messageBytes) {
    //get message content bytes
    byte[] stringBytes = Arrays.copyOfRange(messageBytes, 0, 255);
    //convert to String and trim whitespace
    messageContents = new String(stringBytes).trim();
    sequenceCounter = messageBytes[255];
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
    //convert counter to byte
    byte counterByte = (byte) sequenceCounter;
    //create new array to represent message
    byte[] stringBytes = messageContents.getBytes();
    byte[] mBytes = new byte[256];
    // loop through array
    for(int i = 0; i < 256; i++)
    {
      if( i < stringBytes.length)
      {
        mBytes[i] = stringBytes[i];
      }
      else
      {
        mBytes[i] = 0;
      }
    }
    mBytes[255] = counterByte;
    return mBytes;
  }


}
