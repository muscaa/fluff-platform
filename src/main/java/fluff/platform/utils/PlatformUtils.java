package fluff.platform.utils;

import fluff.platform.INativeLibrary;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

/**
 * Utility class for platform-specific tasks related to native libraries.
 */
public class PlatformUtils {
	
    /**
     * Generates the file name for a native library based on the operating system.
     *
     * @param os the operating system
     * @param lib the native library
     * @return the file name of the native library for the specified operating system
     */
    public static String getFileName(OS os, INativeLibrary lib) {
        return lib.getName() + "." + os.getLibraryExtension();
    }
    
    /**
     * Generates the path for a native library based on the operating system and architecture.
     *
     * @param os the operating system
     * @param arch the architecture
     * @param lib the native library
     * @return the path of the native library for the specified operating system and architecture
     */
    public static String getPath(OS os, OSArch arch, INativeLibrary lib) {
        return os.name().toLowerCase() + "/" + arch.name().toLowerCase() + "/" + getFileName(os, lib);
    }
}
