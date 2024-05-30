package fluff.platform.os;

import java.util.HashMap;
import java.util.Map;

public enum OSArch {
	X32(false),
	X64(true),
	ARM32(false),
	ARM64(true),
	PPC32(false),
	PPC64(true),
	UNKNOWN(false),
	;
	
	private final boolean is64Bit;
	
	private OSArch(boolean is64Bit) {
		this.is64Bit = is64Bit;
	}
	
	public boolean is64Bit() {
		return is64Bit;
	}
	
	public boolean is32Bit() {
		return !is64Bit;
	}
	
	public static final OSArch SYSTEM;
	static {
		Map<String, OSArch> map = new HashMap<>();
        map.put("x86", X32);
        map.put("amd64", X64);
        map.put("x86_64", X64);
        //map.put("ia64", UNKNOWN); // no longer used, why should I?
        map.put("arm", ARM32);
        map.put("aarch64", ARM64);
        map.put("ppc", PPC32);
        map.put("powerpc", PPC32);
        map.put("ppc64", PPC64);
        map.put("ppc64le", PPC64);
        //map.put("sparc", UNKNOWN); // no longer used, why should I?
        //map.put("sparcv9", UNKNOWN); // no longer used, why should I?
        //map.put("mips", UNKNOWN); // no longer used, why should I?
        //map.put("mips64", UNKNOWN); // no longer used, why should I?
        //map.put("s390", UNKNOWN); // no longer used, why should I?
        //map.put("s390x", UNKNOWN); // no longer used, why should I?
        
        String arch = System.getProperty("os.arch");
        
        SYSTEM = map.containsKey(arch) ? map.get(arch) : UNKNOWN;
	}
}
