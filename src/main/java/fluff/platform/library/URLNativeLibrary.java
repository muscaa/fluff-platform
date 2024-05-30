package fluff.platform.library;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import fluff.platform.INativeLibrary;
import fluff.platform.LibraryPaths;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.LibraryUtils;

public class URLNativeLibrary implements INativeLibrary {
	
	private final URL baseURL;
	private final String name;
	private final LibraryPaths<URL> paths;
	
	public URLNativeLibrary(URL baseURL, String name) throws MalformedURLException {
		this.baseURL = baseURL;
		this.name = name;
		this.paths = new LibraryPaths<>();
		
		for (OS os : OS.values()) {
			for (OSArch arch : os.getArchitectures()) {
				set(os, arch, new URL(this.baseURL, LibraryUtils.getPath(os, arch, this)));
			}
		}
	}
	
	@Override
	public InputStream getInputStream(OS os, OSArch arch) throws IOException {
		URL url = get(os, arch);
		if (url == null) return null;
		
		return url.openStream();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public URL get(OS os, OSArch arch) {
		return paths.get(os, arch);
	}
	
	public boolean set(OS os, OSArch arch, URL url) {
		return paths.set(os, arch, url);
	}
}
