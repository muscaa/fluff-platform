package fluff.platform;

import java.util.ArrayList;
import java.util.List;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

public class LibraryPaths<V> {
	
	private final List<V> paths;
	
	public LibraryPaths() {
		int size = 0;
		for (OS os : OS.values()) {
			size += os.getArchitectures().length;
		}
		this.paths = new ArrayList<>(size);
	}
	
	public V get(OS os, OSArch arch) {
		if (os == OS.UNKNOWN || arch == OSArch.UNKNOWN || !os.isSupported(arch)) return null;
		return paths.get(getIndex(os, arch));
	}
	
	public boolean set(OS os, OSArch arch, V path) {
		if (os == OS.UNKNOWN || arch == OSArch.UNKNOWN || !os.isSupported(arch)) return false;
		paths.set(getIndex(os, arch), path);
		return true;
	}
	
	protected int getIndex(OS os, OSArch arch) {
		int index = 0;
		for (OS os0 : OS.values()) {
			if (os0 == os) return index + os0.getArchIndex(arch);
			
			index += os0.getArchitectures().length;
		}
		return -1;
	}
}
