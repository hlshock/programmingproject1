import java.util.*;
import java.nio.ByteBuffer;
/**
* Represents a message:
* first 255 bytes = string contents
* next 1 byte = message counter
*/
public class Message {
  /**
  * messageContents: string contents of message
  * sequenceCounter: keeps track of counter of messages
  */
  String messageContents;
  int sequenceCounter;

  /**
  * Creates message to send
  * @param message string contents
  * @param count   counter for message
  */
  public Message(String message, int count) {
    messageContents = message;
    sequenceCounter = count;
  }

  /**
  * Creates message from recieved byte array
  * @param messageBytes byte array containg message data
  */
  public Message(byte[] messageBytes) {
    //get message content bytes
    byte[] stringBytes = Arrays.copyOfRange(messageBytes, 0, 259);
    //convert to String and trim whitespace
    messageContents = new String(stringBytes).trim();
    byte[] counterBytes = Arrays.copyOfRange(messageBytes, 256, 260);
    sequenceCounter = ByteBuffer.wrap(counterBytes).getInt();
  }

  /**
  * @return sequenceCounter
  */
  public int getSequenceCounter() {
    return sequenceCounter;
  }
  /**
  * sets sequence counter
  * @param counter value to set counter to
  */
  public void setSequenceCounter(int counter) {
    sequenceCounter = counter;
  }
  /**
  * @return String containing message contents
  */
  public String getMessageContents() {
    return messageContents;
  }

  /**
  * sets message contents
  * @param message String containing desired contents
  */
  public void setMessageContents(String message) {
    messageContents = message;
  }

  /**
  * @return the byte array representation of this Message object
  */
  public byte[] getBytes() {
    //convert counter to byte
    byte[] counterBytes = convertIntToBytes(sequenceCounter);
    //create new array to represent message
    byte[] stringBytes = messageContents.getBytes();
    byte[] mBytes = new byte[260];
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
    int counterIndex = 256;
    for (int i = 0; i < 4; i++){
      mBytes[counterIndex] = counterBytes[i];
      counterIndex++;
    }
    return mBytes;
  }
  /**
   * Converts integer to byte array
   * @param  i integer to convert
   * @return   the byte array
   */
  private byte[] convertIntToBytes(int i){
    ByteBuffer intBytes = ByteBuffer.allocate(4);
    intBytes.putInt(i);
    return intBytes.array();
  }
}
