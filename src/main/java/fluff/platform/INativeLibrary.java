package fluff.platform;

import java.io.IOException;
import java.io.InputStream;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

public interface INativeLibrary {
	
	InputStream getInputStream(OS os, OSArch arch) throws IOException;
	
	String getName();
}
