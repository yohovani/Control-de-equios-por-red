/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_acuña;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yohovani vargas
 */
public class clienteControl {
	private int puerto;
	private String grupo;
	private MulticastSocket socket;
	
		
	public static void main(String args[]){
		clienteControl c = new clienteControl(55400,"225.3.4.5");
		while(true){
			c.recibirRespuesta();
		}
	}

	public clienteControl(int puerto, String grupo) {
		try {
			this.puerto = puerto;
			this.grupo = grupo;
			this.socket = new MulticastSocket(this.puerto);
			this.socket.joinGroup(InetAddress.getByName(this.grupo));
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void enviarMensaje(String msj){
		try {
			byte[] buffer = msj.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(this.grupo),this.puerto);
			socket.send(dp);
		} catch (UnknownHostException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void recibirRespuesta(){
		try {
			byte[] buffer = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(this.grupo),this.puerto);
			socket.receive(dp);
			String mensaje = new String(buffer);
			System.out.println(mensaje);
			operacion(mensaje);
		} catch (UnknownHostException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void operacion(String mensaje){
		try {
			String[] datos = mensaje.split(",");
			InetAddress ip = InetAddress.getLocalHost();
			if(datos[0].equals(ip.getHostAddress())){
				String comando;
				if(System.getProperty("os.name").contains("Windows")){
					if(datos[1].contains("apagar")){
						comando = "shutdown -s -t 600";
					}else{
						if(datos[1].contains("reiniciar")){
							comando = "shutdown -r";
						}else{
							comando = datos[1];
						}
					}

				}else{
					if(datos[1].contains("apagar")){
						comando = "halt";
					}else{
						if(datos[1].contains("reiniciar")){
							comando = "reboot";
						}else{
							comando = datos[1];
						}
					}
				}
				System.out.println(comando);
				try {
					Runtime.getRuntime().exec(comando);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
			
		} catch (UnknownHostException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public MulticastSocket getSocket() {
		return socket;
	}

	public void setSocket(MulticastSocket socket) {
		this.socket = socket;
	}
}
