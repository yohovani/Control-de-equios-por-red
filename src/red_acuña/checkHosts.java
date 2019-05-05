/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_acuña;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yohov
 */
public class checkHosts {
	private ArrayList<String> equiposRed;
	private int timeout;
	private String net;

	public checkHosts(String net,int to) {
		this.equiposRed = new ArrayList();
		this.net = net;
		this.timeout = to;
	}
	
	public void checkHosts() throws IOException {
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 1; i < 28; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 28; i < 56; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 56; i < 84; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r4 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 84; i < 112; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r5 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 112; i < 140; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r6 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 140; i < 168; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r7 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 168; i < 196; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r8 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 196; i < 224; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Runnable r9 = new Runnable() {
			@Override
			public void run() {
				InetAddress ip;
				for (int i = 224; i < 256; i++) {
					System.out.println(i);
					try {
						String host = net + "." + i;
						if (InetAddress.getByName(host).isReachable(timeout)) {
							equiposRed.add(host);
						}
					} catch (UnknownHostException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IOException ex) {
						Logger.getLogger(checkHosts.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		Thread t4 = new Thread(r4);
		Thread t5 = new Thread(r5);
		Thread t6 = new Thread(r6);
		Thread t7 = new Thread(r7);
		Thread t8 = new Thread(r8);
		Thread t9 = new Thread(r9);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		while(t1.isAlive() || t2.isAlive() || t3.isAlive() && t4.isAlive() || t5.isAlive() || t6.isAlive() || t7.isAlive() || t8.isAlive() || t9.isAlive()){}
	}

	public ArrayList<String> getEquiposRed() {
		return equiposRed;
	}

	public void setEquiposRed(ArrayList<String> equiposRed) {
		this.equiposRed = equiposRed;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}
	
	
}
