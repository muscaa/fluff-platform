package fluff.platform.library;

import java.io.IOException;
import java.io.InputStream;

import fluff.platform.INativeLibrary;
import fluff.platform.NativeLibraryPaths;
import fluff.platform.os.OS;
import fluff.platform.os.OSArch;
import fluff.platform.utils.PlatformUtils;

/**
 * Represents a native library bundled with the application.
 */
public class BundledNativeLibrary implements INativeLibrary {
    
    private final ClassLoader classLoader;
    private final String name;
    private final NativeLibraryPaths<String> paths;
    
    /**
     * Constructs a new BundledNativeLibrary with the specified class loader and name.
     *
     * @param classLoader the class loader used to load the native library
     * @param name the name of the native library
     */
    public BundledNativeLibrary(ClassLoader classLoader, String name) {
        this.classLoader = classLoader;
        this.name = name;
        this.paths = new NativeLibraryPaths<>();
        
        for (OS os : OS.values()) {
            for (OSArch arch : os.getArchitectures()) {
                set(os, arch, "/natives/" + PlatformUtils.getPath(os, arch, this));
            }
        }
    }
    
    /**
     * Constructs a new BundledNativeLibrary with the system class loader and specified name.
     *
     * @param name the name of the native library
     */
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
    
    /**
     * Retrieves the path for the native library for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the architecture
     * @return the path of the native library
     */
    public String get(OS os, OSArch arch) {
        return paths.get(os, arch);
    }
    
    /**
     * Sets the path for the native library for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the architecture
     * @param path the path to set
     * @return true if the path was successfully set, false otherwise
     */
    public boolean set(OS os, OSArch arch, String path) {
        return paths.set(os, arch, path);
    }
}
