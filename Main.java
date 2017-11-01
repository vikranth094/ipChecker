import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String gateway="10.110.95.253",netmask="255.255.248.0",checkIp="10.110.93.191";
		System.out.println(Validate(gateway,netmask,checkIp));
		
		
		

	}
	public static int binaryToInteger(int[] numbers) {
	    
	    int result = 0;
	    for(int i=7; i>=0; i--)
	        if(numbers[i]==1)
	            result += Math.pow(2, (7-i ));
	    return result;
	}
	public static int[] convertTobinary(int number) {
	    int[] binary = new int[8];
	    for (int i = 7, num = number; i >= 0; i--, num >>>= 1)
	        binary[i] = num & 1;
	    return binary;
	}
	
	public static long ipToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;
		for (byte octet : octets) {
			
			result <<= 8;
			result |= octet & 0xff;
		}
		return result;
	}
		

	public static boolean isValidRange(String ipStart, String ipEnd,
			String ipToCheck) {
		try {
			long ipLo = ipToLong(InetAddress.getByName(ipStart));
			long ipHi = ipToLong(InetAddress.getByName(ipEnd));
			long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
			return (ipToTest >= ipLo && ipToTest <= ipHi);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean Validate(String gateway,String netmask,String checkIp)
	{
StringBuffer low =new StringBuffer(),high=new StringBuffer();
		
		
		String[] gatewayoctets = gateway.split("\\.");
		String[] netmaskoctets =netmask.split("\\.");
		int storeResult[]=new int[8];
	
		for (int i=0; i<4;i++) {
			
		
			
			int[] gatewaybinary=convertTobinary(Integer.parseInt(gatewayoctets[i]));
			int[] netmaskbinary=convertTobinary(Integer.parseInt(netmaskoctets[i]));
			//calculating low adddress
			for(int j=0;j<8;j++)
			{
				if(netmaskbinary[j]==0)
				{
					storeResult[j]=0;
				}
				else
				{
					storeResult[j]=gatewaybinary[j];
				}
				
				
				
			}
			if(i==3)low.append(String.valueOf(binaryToInteger(storeResult)));
			else
			low.append(String.valueOf(binaryToInteger(storeResult))+".");
			
			//calculating high address
			for(int j=0;j<8;j++)
			{
				if(netmaskbinary[j]==0)
				{
					storeResult[j]=1;
				}
				else
				{
					storeResult[j]=gatewaybinary[j];
				}
				
				
				
			}
			if(i==3)high.append(String.valueOf(binaryToInteger(storeResult)));
			else
			high.append(String.valueOf(binaryToInteger(storeResult))+".");
			
		}
		System.out.println("high address "+high+"\n"+"low  address "+low);
		return(isValidRange(low.toString(), high.toString(),checkIp));
		
	}

}
