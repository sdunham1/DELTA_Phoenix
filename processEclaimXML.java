import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.delta.eclaim.domain.common.CheckNullException;
import com.delta.schemas.common.baggagefiletypes.v1.AddressInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.BagDetailsInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.BagInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.ContentsInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.ItineraryInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.PassengerInfoType;
import com.delta.schemas.common.baggagefiletypes.v1.PhoneNumberInfoType;

public class processEclaimXML {

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

				while (line != null) {
					sb.append(line);
					if (line.contains("</ns2:CreateClaimRequestInfo>") || line.contains("</v1:CreateClaimRequestInfo>") ) {
						String responseString = "";
						com.delta.schemas.common.baggagefiletypes.v1.CreateClaimRequestInfoType claimRequestObj = com.delta.schemas.common.baggagefiletypes.v1.JaxbMain
								.xmlToObj(sb.toString());
	
						/** Get PassengerInfo **/
						PassengerInfoType passengerInfoObj = claimRequestObj
								.getPassengerInfo();
	
						/** Get AddressInfo **/
						List<AddressInfoType> addressInfoList = claimRequestObj
								.getAddressInfo();
	
						/** Get PhoneNumberInfo **/
						List<PhoneNumberInfoType> phoneNumberInfoList = claimRequestObj
								.getPhoneNumberInfo();
	
						/** Get passengerItineraryInfo **/
						List<ItineraryInfoType> passengerItineraryInfoList = claimRequestObj
								.getPassengerItineraryInfo();
	
						/** Get DisclaimerInfoType **/
						// List<DisclaimerInfoType> disclaimerInfoList =
						// claimRequestObj.getDisclaimerInfo();
	
						/** Get bagInfoInfo **/
						List<BagInfoType> bagInfoList = claimRequestObj.getBagInfo();
	
						/** Get DamagePilferageInfo **/
						// DamagePilferageInfoType damagePilferageInfoObj =
						// claimRequestObj.getDamagePilferageInfo();
	
						/** Get LossDetailsInfoType **/
						// LossDetailsInfoType lossDetailsInfoTypeObj =
						// claimRequestObj.getLossDetailsInfo();
	
						/** Get BagDetailsInfoType **/
						List<BagDetailsInfoType> bagDetailsInfoList = claimRequestObj
								.getBagDetailsInfo();
	
						/** Get ContentsInfoType **/
						List<ContentsInfoType> contentsInfoList = claimRequestObj
								.getContentsInfo();
	
						/**
						 * get the value from XML for displaying reply XML while error
						 * is throw
						 */
						
						
						if (phoneNumberInfoList.size() > 0){
	
							if (phoneNumberInfoList.get(0).getPhoneNumber() != null
									&& phoneNumberInfoList.get(0).getPhoneNumber() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + phoneNumberInfoList.get(0).getPhoneNumber();
							} else {
								responseString = responseString + ",";
							}
						}
						
						if (addressInfoList.size() > 0){
	
							if (addressInfoList.get(0).getStreetAddressLine1() != null
									&& addressInfoList.get(0).getStreetAddressLine1() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + addressInfoList.get(0).getStreetAddressLine1();
							} else {
								responseString = responseString + ",";
							}
	//						if (addressInfoList.get(0).getStreetAddressLine2() != null
	//								&& addressInfoList.get(0).getStreetAddressLine2() != "") {
	//							if (responseString.length() > 0){
	//								responseString = responseString + ",";
	//							}
	//							responseString = responseString + "address_line_2=" + addressInfoList.get(0).getStreetAddressLine2();
	//						}
							if (addressInfoList.get(0).getCity() != null
									&& addressInfoList.get(0).getCity() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + addressInfoList.get(0).getCity();
							} else {
								responseString = responseString + ",";
							}
							if (addressInfoList.get(0).getPostalCode() != null
									&& addressInfoList.get(0).getPostalCode() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + addressInfoList.get(0).getPostalCode();
							} else {
								responseString = responseString + ",";
							}
							if (addressInfoList.get(0).getStateProvince() != null
									&& addressInfoList.get(0).getStateProvince() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + addressInfoList.get(0).getStateProvince();
							} else {
								responseString = responseString + ",";
							}
							if (addressInfoList.get(0).getCountry() != null
									&& addressInfoList.get(0).getCountry() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + addressInfoList.get(0).getCountry();
							} else {
								responseString = responseString + ",";
							}
						}
						
						if (passengerInfoObj.getEmailAddress() != null
								&& passengerInfoObj.getEmailAddress() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + passengerInfoObj.getEmailAddress();
						} else {
							responseString = responseString + ",";
						}
						if (passengerInfoObj.getLoyaltyMemberNumber() != null
								&& passengerInfoObj.getLoyaltyMemberNumber() != "" && passengerInfoObj.getLoyaltyMemberNumber().length() == 10) {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + passengerInfoObj.getLoyaltyMemberNumber();
						} else {
							responseString = responseString + ",";
						}
						if (passengerInfoObj.getFirstName() != null
								&& passengerInfoObj.getFirstName() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + passengerInfoObj.getFirstName();
						} else {
							responseString = responseString + ",";
						}
						if (passengerInfoObj.getLastName() != null
								&& passengerInfoObj.getLastName() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + passengerInfoObj.getLastName();
						} else {
							responseString = responseString + ",";
						}
						
						responseString = responseString + ",USD";  //Currency until we determine which one
						
						if (claimRequestObj.getFileReferenceNumber() != null
								&& claimRequestObj.getFileReferenceNumber() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + claimRequestObj.getFileReferenceNumber();
						} else {
							responseString = responseString + ",";
						}
						if (claimRequestObj.getClaimType() != null
								&& claimRequestObj.getClaimType() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + claimRequestObj.getClaimType();;
						} else {
							responseString = responseString + ",";
						}
						responseString = responseString + ",";  //WorldTracer is blank
						if (passengerInfoObj.getTicketNumber() != null
								&& passengerInfoObj.getTicketNumber() != "") {
							if (responseString.length() > 0){
								responseString = responseString + ",";
							}
							responseString = responseString + passengerInfoObj.getTicketNumber();
						} else {
							responseString = responseString + ",";
						}
						if (passengerItineraryInfoList.size() > 0){
							if (passengerItineraryInfoList.get(0).getArrivalAirportCode() != null
									&& passengerItineraryInfoList.get(0).getArrivalAirportCode() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + passengerItineraryInfoList.get(0).getArrivalAirportCode();
							} else {
								responseString = responseString + ",";
							}
							if (passengerItineraryInfoList.get(0).getDepartureAirportCode() != null
									&& passengerItineraryInfoList.get(0).getDepartureAirportCode() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + passengerItineraryInfoList.get(0).getDepartureAirportCode();
							} else {
								responseString = responseString + ",";
							}
							if (passengerItineraryInfoList.get(0).getOperatingAirlineCode() != null
									&& passengerItineraryInfoList.get(0).getOperatingAirlineCode() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + passengerItineraryInfoList.get(0).getOperatingAirlineCode();
							} else {
								responseString = responseString + ",";
							}
							if (passengerItineraryInfoList.get(0).getOperatingFlightNumber() != null
									&& passengerItineraryInfoList.get(0).getOperatingFlightNumber() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString + passengerItineraryInfoList.get(0).getOperatingFlightNumber();
							} else {
								responseString = responseString + ",";
							}
							if (passengerItineraryInfoList.get(0).getFlightDate() != null
									&& passengerItineraryInfoList.get(0).getFlightDate() != "") {
								if (responseString.length() > 0){
									responseString = responseString + ",";
								}
								responseString = responseString +  passengerItineraryInfoList.get(0).getFlightDate();
							} else {
								responseString = responseString + ",";
							}
						}
	//					if (passengerInfoObj.getMiddleName() != null
	//							&& passengerInfoObj.getMiddleName() != "") {
	//						if (responseString.length() > 0){
	//							responseString = responseString + ",";
	//						}
	//						responseString = responseString + "psgr_mdl_nme=" + passengerInfoObj.getMiddleName();
	//					}
	//					if (passengerInfoObj.getTitle() != null
	//							&& passengerInfoObj.getTitle() != "") {
	//						if (responseString.length() > 0){
	//							responseString = responseString + ",";
	//						}
	//						responseString = responseString + passengerInfoObj.getTitle();
	//					} else {
	//						responseString = responseString + ",";
	//					}
	
	
						writer.println(responseString);
						sb = new StringBuilder();
						line = br.readLine();
					} else {
						line = br.readLine();
					}
				}


			} catch (Exception e) {
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

		}
	}

}
