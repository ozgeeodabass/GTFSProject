package ime.gtfs.core.enums;

public enum RouteType {
	TRAM,
	SUBWAY,
	RAIL,
	BUS,
	FERRY,
	CABLETRAM;
	
	public static String getValue(int index) {
		switch (index) {
		case 1: {
			
			return "TRAM";
		}case 2: {
			
			return "SUBWAY";
		}case 3: {
			
			return "RAIL";
		}case 4: {
			
			return "BUS";
		}case 5: {
			
			return "FERRY";
		}case 6: {
			
			return "CABLETRAM";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + index);
		}
}
	
	
}


