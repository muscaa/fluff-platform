package fluff.platform.library;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import fluff.platform.INativeLibrary;
import fluff.platform.NativeLibraryPaths;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.PlatformUtils;

/**
 * Represents a native library that can be accessed via a URL.
 */
public class URLNativeLibrary implements INativeLibrary {
    
    private final URL baseURL;
    private final String name;
    private final NativeLibraryPaths<URL> paths;
    
    /**
     * Constructs a new URLNativeLibrary with the specified base URL and name.
     *
     * @param baseURL the base URL where the native library is located
     * @param name the name of the native library
     * @throws MalformedURLException if the URL is malformed
     */
    public URLNativeLibrary(URL baseURL, String name) throws MalformedURLException {
        this.baseURL = baseURL;
        this.name = name;
        this.paths = new NativeLibraryPaths<>();
        
        for (OS os : OS.values()) {
            for (OSArch arch : os.getArchitectures()) {
                set(os, arch, new URL(this.baseURL, PlatformUtils.getPath(os, arch, this)));
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
    
    /**
     * Retrieves the URL for the native library for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the architecture
     * @return the URL of the native library
     */
    public URL get(OS os, OSArch arch) {
        return paths.get(os, arch);
    }
    
    /**
     * Sets the URL for the native library for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the architecture
     * @param url the URL to set
     * @return true if the URL was successfully set, false otherwise
     */
    public boolean set(OS os, OSArch arch, URL url) {
        return paths.set(os, arch, url);
    }
}
