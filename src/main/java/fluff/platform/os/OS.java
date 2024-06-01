package fluff.platform.os;

/**
 * Represents various operating systems and their supported architectures.
 */
public enum OS {
    WINDOWS("dll", OSArch.X32, OSArch.X64, OSArch.ARM64),
    LINUX("so", OSArch.X32, OSArch.X64),
    OSX("dylib", OSArch.X32, OSArch.X64, OSArch.ARM64), // ARM64 - apple silicon
    ANDROID("so", OSArch.ARM32, OSArch.ARM64),
    IOS("dylib", OSArch.ARM32, OSArch.ARM64),
    SOLARIS("so", OSArch.X32, OSArch.X64),
    FREEBSD("so", OSArch.X32, OSArch.X64),
    AIX("so", OSArch.PPC32, OSArch.PPC64),
    UNKNOWN(null);
    
    private final String libExtension;
    private final OSArch[] architectures;
    
    /**
     * Constructs an OS enum with the specified library extension and supported architectures.
     *
     * @param libExtension the library file extension for this OS
     * @param architectures the supported architectures for this OS
     */
    private OS(String libExtension, OSArch... architectures) {
        this.libExtension = libExtension;
        this.architectures = architectures;
    }
    
    /**
     * Gets the library file extension for this OS.
     *
     * @return the library file extension
     */
    public String getLibraryExtension() {
        return libExtension;
    }
    
    /**
     * Gets the supported architectures for this OS.
     *
     * @return an array of supported architectures
     */
    public OSArch[] getArchitectures() {
        return architectures;
    }
    
    /**
     * Gets the index of the specified architecture in the list of supported architectures.
     *
     * @param arch the architecture to find the index for
     * @return the index of the architecture, or -1 if not supported
     */
    public int getArchIndex(OSArch arch) {
        for (int i = 0; i < architectures.length; i++) {
            if (architectures[i] == arch) return i;
        }
        return -1;
    }
    
    /**
     * Checks if the specified architecture is supported by this OS.
     *
     * @param arch the architecture to check
     * @return true if the architecture is supported, false otherwise
     */
    public boolean isSupported(OSArch arch) {
        return getArchIndex(arch) != -1;
    }
    
    /**
     * The system's operating system.
     */
    public static final OS SYSTEM;
    
    static {
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os.contains("win")) SYSTEM = WINDOWS;
        else if (os.contains("nix") || os.contains("nux")) SYSTEM = LINUX;
        else if (os.contains("mac")) SYSTEM = OSX;
        else if (os.contains("android")) SYSTEM = ANDROID;
        else if (os.contains("ios")) SYSTEM = IOS;
        else if (os.contains("sunos")) SYSTEM = SOLARIS;
        else if (os.contains("freebsd")) SYSTEM = FREEBSD;
        else if (os.contains("aix")) SYSTEM = AIX;
        else SYSTEM = UNKNOWN;
    }
}
