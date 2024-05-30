package fluff.platform.os;

public enum OS {
	WINDOWS("dll", OSArch.X32, OSArch.X64, OSArch.ARM64),
	LINUX("so", OSArch.X32, OSArch.X64),
	OSX("dylib", OSArch.X32, OSArch.X64, OSArch.ARM64), // ARM64 - apple silicon
	ANDROID("so", OSArch.ARM32, OSArch.ARM64),
	IOS("dylib", OSArch.ARM32, OSArch.ARM64),
	SOLARIS("so", OSArch.X32, OSArch.X64),
	FREEBSD("so", OSArch.X32, OSArch.X64),
	AIX("so", OSArch.PPC32, OSArch.PPC64),
	UNKNOWN(null),
	;
	
	private final String libExtension;
	private final OSArch[] architectures;
	
	private OS(String libExtension, OSArch... architectures) {
		this.libExtension = libExtension;
		this.architectures = architectures;
	}
	
	public String getLibraryExtension() {
		return libExtension;
	}
	
	public OSArch[] getArchitectures() {
		return architectures;
	}
	
	public int getArchIndex(OSArch arch) {
		for (int i = 0; i < architectures.length; i++) {
			if (architectures[i] == arch) return i;
		}
		return -1;
	}
	
	public boolean isSupported(OSArch arch) {
		return getArchIndex(arch) != -1;
	}
	
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
