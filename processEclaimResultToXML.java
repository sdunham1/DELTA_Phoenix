import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.delta.eclaim.domain.common.CheckNullException;
import com.delta.eclaim.reply.jaxb.ClaimInfo;

public class processEclaimResultToXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CheckNullException exception = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					args[0]));
			PrintWriter writer = new PrintWriter(args[1]);
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();
				JAXBContext contextObj = JAXBContext.newInstance(ClaimInfo.class); 
				Marshaller marshallerObj = contextObj.createMarshaller();
				//marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				while (line != null) {
					String[] values = line.split(",");
					ClaimInfo claim = new ClaimInfo();
					if (values.length > 6){
						claim.setMiddleName("");
						claim.setLastName(values[1]);
						claim.setClaimType(values[2]);
						claim.setErrorReason(values[3]);
						claim.setFileNumber(values[4]);
						claim.setFirstName(values[5]);
						claim.setCaseNumber(values[6]);
						StringWriter sw = new StringWriter();
						marshallerObj.marshal(claim, sw);
						String responseString = sw.toString();
						writer.println(responseString);
					} else if (values.length > 5){
						claim.setMiddleName("");
						claim.setLastName(values[1]);
						claim.setClaimType(values[2]);
						claim.setErrorReason(values[3]);
						claim.setFileNumber(values[4]);
						claim.setFirstName(values[5]);
						claim.setCaseNumber("");
						StringWriter sw = new StringWriter();
						marshallerObj.marshal(claim, sw);
						String responseString = sw.toString();
						writer.println(responseString);
					} else {
						System.out.println("bad data");
					}
					line = br.readLine();

				}

			} catch (Exception e) {
				e.printStackTrace();
				System.exit(99);
			} catch (Throwable e) {
				System.exit(99);
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				writer.close();
			}

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
