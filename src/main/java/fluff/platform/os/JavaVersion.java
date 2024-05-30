package fluff.platform.os;

public class JavaVersion {
	
	private final int version;
	private final boolean is64Bit;
	
	public JavaVersion(int version, boolean is64Bit) {
		this.version = version;
		this.is64Bit = is64Bit;
	}
	
	public int getVersion() {
		return version;
	}
	
	public boolean is64Bit() {
		return is64Bit;
	}
	
	public boolean is32Bit() {
		return !is64Bit;
	}
	
	public static final JavaVersion SYSTEM;
	static {
	    String version = System.getProperty("java.version");
	    String javaArch = System.getProperty("sun.arch.data.model");
	    
	    if (version.startsWith("1.")) {
	        version = version.substring(2, 3);
	    } else {
	        int dot = version.indexOf(".");
	        if (dot != -1) {
	        	version = version.substring(0, dot);
	        }
	    }
	    
	    SYSTEM = new JavaVersion(Integer.parseInt(version), javaArch.equals("64"));
	}
}
