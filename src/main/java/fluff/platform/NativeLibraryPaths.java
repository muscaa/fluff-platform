package fluff.platform;

import java.util.ArrayList;
import java.util.List;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

/**
 * Manages paths to native libraries for different operating systems and architectures.
 *
 * @param <V> the type of the path values
 */
public class NativeLibraryPaths<V> {
    
    private final List<V> paths;
    
    /**
     * Constructs a new NativeLibraryPaths instance.
     */
    public NativeLibraryPaths() {
        int size = 0;
        for (OS os : OS.values()) {
            size += os.getArchitectures().length;
        }
        this.paths = new ArrayList<>(size);
    }
    
    /**
     * Retrieves the path for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the operating system architecture
     * @return the path for the specified operating system and architecture, or null if not found
     */
    public V get(OS os, OSArch arch) {
        if (os == OS.UNKNOWN || arch == OSArch.UNKNOWN || !os.isSupported(arch)) return null;
        return paths.get(getIndex(os, arch));
    }
    
    /**
     * Sets the path for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the operating system architecture
     * @param path the path to set
     * @return true if the path was set successfully, false otherwise
     */
    public boolean set(OS os, OSArch arch, V path) {
        if (os == OS.UNKNOWN || arch == OSArch.UNKNOWN || !os.isSupported(arch)) return false;
        paths.set(getIndex(os, arch), path);
        return true;
    }
    
    /**
     * Gets the index for the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the operating system architecture
     * @return the index for the specified operating system and architecture
     */
    protected int getIndex(OS os, OSArch arch) {
        int index = 0;
        for (OS os0 : OS.values()) {
            if (os0 == os) return index + os0.getArchIndex(arch);
            
            index += os0.getArchitectures().length;
        }
        return -1;
    }
}
