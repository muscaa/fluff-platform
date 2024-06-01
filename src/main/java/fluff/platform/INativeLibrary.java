package fluff.platform;

import java.io.IOException;
import java.io.InputStream;

import fluff.platform.os.OS;
import fluff.platform.os.OSArch;

/**
 * Represents a native library that can be loaded based on the operating system and architecture.
 */
public interface INativeLibrary {
    
    /**
     * Retrieves an input stream for the native library based on the specified operating system and architecture.
     *
     * @param os the operating system
     * @param arch the operating system architecture
     * @return an input stream for the native library
     * @throws IOException if an I/O error occurs
     */
    InputStream getInputStream(OS os, OSArch arch) throws IOException;
    
    /**
     * Retrieves the name of the native library.
     *
     * @return the name of the native library
     */
    String getName();
}
