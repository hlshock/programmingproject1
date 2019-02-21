# programmingproject1

## Testing
The following steps should be followed to run the testing classes we wrote.
1. Compile and launch ServerTesting.
2. In a separate terminal window, compile and launch NetworkTesting
2. Then compile and launch ClientTesting in another terminal window.

Output:
+ in the Server, UnreliableNetwork, and DropResponse classes there is a boolean
testing, which can be set to true to see more output (will work on)
+ When testing is set to false, the testing output can be read from the ClientTesting window.

+ Each checkpoint is contained in a method.  
+ Each checkpoint begins a new Server, Client, and varied other classes necessary for that checkpoint, so the counters will begin at zero for each checkpoint. Thus, the last message sent in each checkpoint's testing must be an "end" message

Checkpoint 1:
- The Client communicates directly with the Server and sends 4 messages:
- Each message will result in a "SUCCESS" if the Client successfully received the response for that message. If the Client doesn't receive the response from the server for a message, the output will read "FAILURE"

Checkpoint 2:
- The Client and Server communicate through an DropMessagesNetwork object, which drops messages from the client at an inputted rate
- The DropMessagesNetwork constructor's second parameter is what percent of the time the network should drop messages
      + We currently have it set to 50, so the UnreliableNetwork drops messages 50% of the time
- The Client send 4 messages total. It sends each message and if it doesn't receive a response from the server within 5 seconds, it will resend the message

Checkpoint 3:
- The Client and Server communicate through an UnreliableNetwork object, which alternatively to the DropMessagesNetwork class, drops both messages from the client and responses from the server at two different inputted rates
- The UnreliableNetwork constructor's second & third parameters are what percents of the time the network should drop messages and responses (2nd parameter is for responses, 3rd parameter is for messages)
      + We currently have both set to 50, so the DropResponses drops messages and responses 50% of the time
- The Client send 4 messages total. It sends each message and if it doesn't receive a response from the server within 5 seconds, it will resend the message
- The Server check each message's counter to see if this is a repeated message, and only increments its counter if a message has not been received before

Checkpoint 4:
- Similar to in checkpoint 3, the Client and Server communicate through an UnreliableNetwork object
- Messages containing Strings longer than 255 characters are split up and sent as smaller messageString
-In reading the testing for this checkpoint, a message is only a success if all divided sections of the long message are sent to the server successfully, and the client successfully received responses for all sections
- The Client send 5 messages, with the last one being "end", so only 4 long messages are sent
