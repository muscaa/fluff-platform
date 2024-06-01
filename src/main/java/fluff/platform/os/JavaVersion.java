package fluff.platform.os;

/**
 * Represents a Java version with its major version number and architecture (32-bit or 64-bit).
 */
public class JavaVersion {
    
    private final int version;
    private final boolean is64Bit;
    
    /**
     * Constructs a new JavaVersion instance.
     *
     * @param version the major version number of the Java version
     * @param is64Bit true if the Java version is 64-bit, false if it is 32-bit
     */
    public JavaVersion(int version, boolean is64Bit) {
        this.version = version;
        this.is64Bit = is64Bit;
    }
    
    /**
     * Gets the major version number of the Java version.
     *
     * @return the major version number
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * Checks if the Java version is 64-bit.
     *
     * @return true if the Java version is 64-bit, false otherwise
     */
    public boolean is64Bit() {
        return is64Bit;
    }
    
    /**
     * Checks if the Java version is 32-bit.
     *
     * @return true if the Java version is 32-bit, false otherwise
     */
    public boolean is32Bit() {
        return !is64Bit;
    }
    
    /**
     * The system's Java version.
     */
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
