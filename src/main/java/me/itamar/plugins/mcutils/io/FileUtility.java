package me.itamar.plugins.mcutils.io;

import java.io.*;

public class FileUtility {

    /**
     * Copies a file/folder from a particular place to a particular place
     * @param source the file/folder that is being copied
     * @param destination the location that the {@code source} is being copied to
     * @throws IOException if a file was not found
     */
    public static void copy(File source, File destination) throws IOException {

        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdir();
            }

            String[] files = source.list();

            if (files == null) {
                return;
            }

            for (String file : files) {
                File newSource = new File(source, file);
                File newDestination = new File(destination, file);
                copy(newSource, newDestination);
            }
        } else {
            try (InputStream in = new FileInputStream(source);
                 OutputStream out = new FileOutputStream(destination)) {
                byte[] buffer = new byte[1024];

                int length;

                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
        }

    }

    /**
     * Deletes a file/folder
     * @param file the file/folder that is being deleted
     */
    public static void delete(File file) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files == null) {
                return;
            }

            for (File child : files) {
                delete(child);
            }

        }

        file.delete();

    }

}
