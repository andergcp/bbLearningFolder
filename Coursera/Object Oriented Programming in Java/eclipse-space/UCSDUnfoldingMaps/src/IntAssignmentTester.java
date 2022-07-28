import demos.SimpleLocation;

public class IntAssignmentTester{
	public static void main (String[] args ){
		SimpleLocation ucsd = new SimpleLocation(32.9, -117.2);
		SimpleLocation kumamoto = new SimpleLocation(32.0, 130.0);
		System.out.println(ucsd + " , " + kumamoto);
		ucsd = kumamoto;
		kumamoto = ucsd;
		System.out.println(ucsd + " , " + kumamoto);
		System.out.println("UCSD: latitud" + ucsd.latitude + " , longitud: " + ucsd.longitude);
		System.out.println("Kumamoto: latitud" + kumamoto.latitude + " , longitud: " + kumamoto.longitude);
	}
}