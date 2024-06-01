package fluff.platform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.PlatformUtils;

/**
 * Provides platform-specific utilities for extracting and loading native libraries.
 */
public class Platform {
    
    private static final Map<INativeLibrary, String> LOADED = new HashMap<>();
    
    /**
     * Extracts the native library to the specified directory.
     *
     * @param dir the directory to extract the library to
     * @param lib the native library to extract
     * @param overwrite whether to overwrite the file if it already exists
     * @return true if the library was successfully extracted, false otherwise
     */
    public static boolean extract(File dir, INativeLibrary lib, boolean overwrite) {
        File libFile = new File(dir, PlatformUtils.getFileName(OS.SYSTEM, lib));
        if (libFile.exists() && !overwrite) return false;
        
        try (InputStream is = lib.getInputStream(OS.SYSTEM, OSArch.SYSTEM)) {
            if (is == null) return false;
            
            FileOutputStream fos = new FileOutputStream(libFile);
            is.transferTo(fos);
            fos.close();
            
            return true;
        } catch (Exception e) {}
        
        return false;
    }
    
    /**
     * Extracts the native library to the specified directory without overwriting existing files.
     *
     * @param dir the directory to extract the library to
     * @param lib the native library to extract
     * @return true if the library was successfully extracted, false otherwise
     */
    public static boolean extract(File dir, INativeLibrary lib) {
        return extract(dir, lib, false);
    }
    
    /**
     * Loads the native library from the specified directory.
     *
     * @param dir the directory to load the library from
     * @param lib the native library to load
     * @return true if the library was successfully loaded, false otherwise
     */
    public static boolean load(File dir, INativeLibrary lib) {
        if (isLoaded(lib)) return false;
        
        File libFile = new File(dir, PlatformUtils.getFileName(OS.SYSTEM, lib));
        if (!libFile.exists()) return false;
        
        String libPath = libFile.getAbsolutePath();
        
        System.load(libPath);
        LOADED.put(lib, libPath);
        
        return true;
    }
    
    /**
     * Extracts and loads the native library from the specified directory.
     *
     * @param dir the directory to extract and load the library from
     * @param lib the native library to extract and load
     * @return true if the library was successfully extracted and loaded, false otherwise
     */
    public static boolean extractAndLoad(File dir, INativeLibrary lib) {
        return extract(dir, lib) ? load(dir, lib) : false;
    }
    
    /**
     * Checks if the native library is already loaded.
     *
     * @param lib the native library to check
     * @return true if the library is already loaded, false otherwise
     */
    public static boolean isLoaded(INativeLibrary lib) {
        return LOADED.containsKey(lib);
    }
}
