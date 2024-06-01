package fluff.platform.os;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents various operating system architectures and their bitness.
 */
public enum OSArch {
    X32(false),
    X64(true),
    ARM32(false),
    ARM64(true),
    PPC32(false),
    PPC64(true),
    UNKNOWN(false);
    
    private final boolean is64Bit;
    
    /**
     * Constructs an OSArch enum with the specified bitness.
     *
     * @param is64Bit true if the architecture is 64-bit, false if 32-bit
     */
    private OSArch(boolean is64Bit) {
        this.is64Bit = is64Bit;
    }
    
    /**
     * Checks if the architecture is 64-bit.
     *
     * @return true if the architecture is 64-bit, false otherwise
     */
    public boolean is64Bit() {
        return is64Bit;
    }
    
    /**
     * Checks if the architecture is 32-bit.
     *
     * @return true if the architecture is 32-bit, false otherwise
     */
    public boolean is32Bit() {
        return !is64Bit;
    }
    
    /**
     * The system's architecture.
     */
    public static final OSArch SYSTEM;
    
    static {
        Map<String, OSArch> map = new HashMap<>();
        map.put("x86", X32);
        map.put("amd64", X64);
        map.put("x86_64", X64);
        map.put("arm", ARM32);
        map.put("aarch64", ARM64);
        map.put("ppc", PPC32);
        map.put("powerpc", PPC32);
        map.put("ppc64", PPC64);
        map.put("ppc64le", PPC64);
        
        // no longer used
        // map.put("ia64", UNKNOWN);
        // map.put("sparc", UNKNOWN);
        // map.put("sparcv9", UNKNOWN);
        // map.put("mips", UNKNOWN);
        // map.put("mips64", UNKNOWN);
        // map.put("s390", UNKNOWN);
        // map.put("s390x", UNKNOWN);
        
        String arch = System.getProperty("os.arch");
        
        SYSTEM = map.containsKey(arch) ? map.get(arch) : UNKNOWN;
    }
}
