package Alexandra_The_Bot;

import javax.speech.*; import java.util.*;
import javax.speech.synthesis.*;

import java.io.DataInputStream; import java.io.InputStream;

import java.net.ServerSocket; import java.net.Socket;
import javax.speech.AudioException; import javax.speech.EngineException;

public class Server {
private static Socket socket; String speaktext;


public static void main(String[] args) throws EngineException, AudioException, InterruptedException{
String str = "";
Server s1 = new Server();

try
{
int port = 3238;
ServerSocket serverSocket = new
 
ServerSocket(port); to the port "+port);

this while(true) loop
 

System.out.println("Server Started and listening

//Server is running always. This is done using while(true)
{
 


InputStreamReader(is); BufferedReader(isr);
 
//Reading the message from the client socket = serverSocket.accept();
/*	InputStream is = socket.getInputStream(); InputStreamReader isr = new

BufferedReader br = new
 
*/

socket.getInputStream(); (s1out);
 

InputStream s1out =

DataInputStream dos = new DataInputStream str = dos.readUTF();
// Call the replaceAll() method str = str.replaceAll("\\s", "");

System.out.println(str); s1.dospeak(str,"kevin16");
 

is "+str);
 
System.out.println("Message received from client

}
 




}
catch (Exception e)
{
e.printStackTrace();
}


 
finally
{
 



try
{

}
 




socket.close();
 
catch(Exception e){}

}
}


public void dospeak(String speak,String	voicename)
{
speaktext=speak;
String voiceName =voicename; try
{
SynthesizerModeDesc desc = new SynthesizerModeDesc(null,"general",	Locale.US,null,null);
Synthesizer synthesizer = Central.createSynthesizer(desc);
synthesizer.allocate(); synthesizer.resume();
desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
Voice[] voices = desc.getVoices(); Voice voice = null;
for (int i = 0; i < voices.length; i++)
{
if (voices[i].getName().equals(voiceName))
{
voice = voices[i]; break;
}
}
synthesizer.getSynthesizerProperties().setVoice(voice); System.out.print("Speaking : "+speaktext); synthesizer.speak(speaktext, null); synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY); synthesizer.deallocate();
}
catch (Exception e)
{
 
String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
System.out.println(""+e); System.out.println(message);
}
}
}
