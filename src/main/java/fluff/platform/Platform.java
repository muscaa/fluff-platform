package fluff.platform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.LibraryUtils;

public class Platform {
    
	private static final Map<INativeLibrary, String> LOADED = new HashMap<>();
	
    public static boolean extract(File dir, INativeLibrary lib, boolean overwrite) {
    	File libFile = new File(dir, LibraryUtils.getFileName(OS.SYSTEM, lib));
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
    
    public static boolean extract(File dir, INativeLibrary lib) {
    	return extract(dir, lib, false);
    }
    
    public static boolean load(File dir, INativeLibrary lib) {
    	if (isLoaded(lib)) return false;
    	
    	File libFile = new File(dir, LibraryUtils.getFileName(OS.SYSTEM, lib));
    	String libPath = libFile.getAbsolutePath();
    	
    	System.load(libPath);
    	LOADED.put(lib, libPath);
    	
    	return true;
    }
    
    public static boolean isLoaded(INativeLibrary lib) {
    	return LOADED.containsKey(lib);
    }
}
