/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package red_acuña;

import java.io.IOException;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class TestSmp {

	Snmp snmp = null;
	String address = null;

	/**
	 * Constructor
	 *
	 * @param add
	 */
	public TestSmp(String add) {
		address = add;
	}

	/**
	 * Port 161 is used for Read and Other operations Port 162 is used for the
	 * trap generation
	 */
	/**
	 * OID - .1.3.6.1.2.1.1.1.0 => SysDec OID - .1.3.6.1.2.1.1.5.0 => SysName
	 */
	public static void main(String[] args) throws IOException {
		TestSmp client;
		checkHosts hosts = new checkHosts("10.5.3",100);
		hosts.checkHosts();
		System.out.println(hosts.getEquiposRed().toString());
		for (int i = 0; i <= hosts.getEquiposRed().size(); i++) {

			try {
				//System.out.println("probando con "+i);
				client = new TestSmp("udp:" + hosts.getEquiposRed().get(i) + "/161");
				client.start();

				//MAC       .1.3.6.1.2.1.2.2.1.6.1
				//Desc      .1.3.6.1.2.1.2.2.1.2.1
				//Nombre    .1.3.6.1.2.1.1.5.0
				//Sofware   .1.3.6.1.2.1.1.1.0
				System.out.println("----------------------------------------------------------------------");
				System.out.println("Nombre de PC: " + client.getAsString(new OID(".1.3.6.1.2.1.1.5.0")));
				System.out.println("Nombre de SO: " + client.getAsString(new OID(".1.3.6.1.2.1.1.1.0")));

				System.out.println("---Adaptadores de Red---");
				for (int k = 1; k < 60; k++) {
					String mac = client.getAsString(new OID(".1.3.6.1.2.1.2.2.1.6." + k));
					String marca = client.getAsString(new OID(".1.3.6.1.2.1.2.2.1.2." + k));

					if (!mac.equals("") && !mac.equals("Null")) {
						System.out.print("Mac: " + mac);
						//System.out.println(marca);
						System.out.println("  Velocidad: " + Integer.parseInt(client.getAsString(new OID(".1.3.6.1.2.1.2.2.1.5." + k))) / 1073741824.00 + " Mb");
					}

				}
			} catch (Exception e) {
				//System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Start the Snmp session. If you forget the listen() method you will not
	 * get any answers because the communication is asynchronous and the
	 * listen() method listens for answers.
	 *
	 * @throws IOException
	 */
	void start() throws IOException {
		TransportMapping transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);
// Do not forget this line!
		transport.listen();
	}

	/**
	 * Method which takes a single OID and returns the response from the agent
	 * as a String.
	 *
	 * @param oid
	 * @return
	 * @throws IOException
	 */
	public String getAsString(OID oid) throws IOException {
		ResponseEvent event = get(new OID[]{oid});
		return event.getResponse().get(0).getVariable().toString();
	}

	/**
	 * This method is capable of handling multiple OIDs
	 *
	 * @param oids
	 * @return
	 * @throws IOException
	 */
	public ResponseEvent get(OID oids[]) throws IOException {
		PDU pdu = new PDU();
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		pdu.setType(PDU.GET);
		ResponseEvent event = snmp.send(pdu, getTarget(), null);
		if (event != null) {
			return event;
		}
		throw new RuntimeException("GET timed out");
	}

	/**
	 * This method returns a Target, which contains information about where the
	 * data should be fetched and how.
	 *
	 * @return
	 */
	private Target getTarget() {
		Address targetAddress = GenericAddress.parse(address);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

}
