package fluff.platform.library;

import java.io.IOException;
import java.io.InputStream;

import fluff.platform.INativeLibrary;
import fluff.platform.LibraryPaths;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.LibraryUtils;

public class BundledNativeLibrary implements INativeLibrary {
	
	private final ClassLoader classLoader;
	private final String name;
	private final LibraryPaths<String> paths;
	
	public BundledNativeLibrary(ClassLoader classLoader, String name) {
		this.classLoader = classLoader;
		this.name = name;
		this.paths = new LibraryPaths<>();
		
		for (OS os : OS.values()) {
			for (OSArch arch : os.getArchitectures()) {
				set(os, arch, "/natives/" + LibraryUtils.getPath(os, arch, this));
			}
		}
	}
	
	public BundledNativeLibrary(String name) {
		this(ClassLoader.getSystemClassLoader(), name);
	}
	
	@Override
	public InputStream getInputStream(OS os, OSArch arch) throws IOException {
		String path = get(os, arch);
    	if (path == null) return null;
    	
    	return classLoader.getResourceAsStream(path);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public String get(OS os, OSArch arch) {
		return paths.get(os, arch);
	}
	
	public boolean set(OS os, OSArch arch, String path) {
		return paths.set(os, arch, path);
	}
}
