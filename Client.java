package Alexandra_The_Bot;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import net.sourceforge.javaflacencoder.FLACFileWriter;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
/**
 * This is where all begins .
 *
 * @author GOXR3PLUS
 *
 */
public class Client{
private static Socket socket;
private final Microphone mic = new
Microphone(FLACFileWriter.FLAC);
private final GSpeechDuplex duplex = new
GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
String oldText = "";
/**
 * Constructor
 */
public Application() {
//Duplex Configuration
duplex.setLanguage("en");
duplex.addResponseListener(new
GSpeechResponseListener() {
public void onResponse(GoogleResponse
googleResponse) {
String output="";
//Get the response from Google Cloud
output = googleResponse.getResponse();
System.out.println(output);
if (output != null) {
makeDecision(output);
} else
System.out.println("Output was
null");
}
}
);
startSpeechRecognition();
}
/**
 * This method makes a decision based on the given text of the
Speech Recognition
 *
 * @param text
 */
public void makeDecision(String output) {
//output = output.trim();
try {
//String serverName = "127.0.0.0"; //
Indicating the place to put Server's IP
socket = new Socket("127.0.0.1", 3238);
//String host="Localhost";
//int port =25000;
//InetAddress address =
InetAddress.getByName(host);
//socket=new Socket(address,port);
/*OutputStream os = socket.getOutputStream();
OutputStreamWriter osw = new
OutputStreamWriter(os);
BufferedWriter bw = new BufferedWriter(osw);
*/
OutputStream s1out = socket.getOutputStream();
DataOutputStream dos = new DataOutputStream
(s1out);
String sendmessage= output;
System.out.println("Client say "+sendmessage);
dos.writeUTF(sendmessage);
System.out.println("Message sent to the
server:"+sendmessage);
}catch(Exception exception){
exception.printStackTrace();
}
finally {
//closing socket
try {
socket.close();
}
catch(Exception e){
e.printStackTrace();
}
}
}
//give the output of what we speak
//System.out.println(output.trim());
//We don't want duplicate responses
public void startSpeechRecognition() {
//Start a new Thread so our application don't lags
new Thread(() -> {
try {
duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
} catch (LineUnavailableException |
InterruptedException e) {
e.printStackTrace();
}
}).start();
}
/**
 * Stops the Speech Recognition
 */
public void stopSpeechRecognition() {
mic.close();
System.out.println("Stopping Speech Recognition...." +
" , Microphone State is:" + mic.getState());
}
public static void main(String[] args) {
new Application();
}
}