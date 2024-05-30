package fluff.platform.utils;

import fluff.platform.INativeLibrary;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

public class LibraryUtils {
	
    public static String getFileName(OS os, INativeLibrary lib) {
    	return lib.getName() + "." + os.getLibraryExtension();
    }
    
    public static String getPath(OS os, OSArch arch, INativeLibrary lib) {
    	return os.name().toLowerCase() + "/" + arch.name().toLowerCase() + "/" + getFileName(os, lib);
    }
}
