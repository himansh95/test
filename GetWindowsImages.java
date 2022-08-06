import java.awt.Dimension;
import java.io.*;
import java.util.*;

public class GetWindowsImages {
    public static void main(String[] args) throws IOException {
        String userProfile = System.getenv("USERPROFILE");
        String sourcePath = userProfile + "\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
        String targetPath = userProfile + "\\Pictures\\Assets";
        String timestampPath = userProfile + "\\Documents\\Java\\timestamp.txt";

        File timestampFile = new File(timestampPath);
        long timestamp = 0 L;

        if (timestampFile.exists()) {
            timestamp = Long.parseLong(new BufferedReader(new FileReader(timestampFile)).readLine());
        }
        final long timestamp0 = timestamp;

        File sourceDirectory = new File(sourcePath);
        File[] filesList = new File[0];
        if (sourceDirectory.exists()) {
            filesList = new File(sourcePath).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.lastModified() > timestamp0;
                }
            });
            System.out.println(timestamp0 + " : " + filesList.length);
        }

        for (File file: filesList) {
            try {
                Dimension dimension = getJPEGDimension(file);

                if (dimension.getWidth() == 1920 && dimension.getHeight() == 1080) {
                    copyFile(file, new File(targetPath + "\\D" + new Date().getTime() + ".jpg"));
                } else if (dimension.getWidth() == 1080 && dimension.getHeight() == 1920) {
                    copyFile(file, new File(targetPath + "\\M\\M" + new Date().getTime() + ".jpg"));
                }
                System.out.println(dimension);
            } catch (Exception e) {
                System.out.println("Exception while copying file: " + file.getName());
                e.printStackTrace();
            }
        }

        if (!timestampFile.exists()) {
            timestampFile.createNewFile();
        }
        String time = new Date().getTime() + "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(timestampFile));
        writer.write(time, 0, time.length());
        writer.flush();
        writer.close();
    }

    public static void copyFile(File source, File target) throws IOException, FileNotFoundException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        int b;

        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fis.close();
        fos.close();
    }

    public static Dimension getJPEGDimension(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);

        // check for SOI marker
        if (fis.read() != 255 || fis.read() != 216) {
            throw new RuntimeException("SOI (Start Of Image) marker 0xff 0xd8 missing");
        }

        Dimension d = null;

        while (fis.read() == 255) {
            int marker = fis.read();
            int len = fis.read() << 8 | fis.read();

            if (marker == 192) {
                fis.skip(1);

                int height = fis.read() << 8 | fis.read();
                int width = fis.read() << 8 | fis.read();

                d = new Dimension(width, height);
                break;
            }

            fis.skip(len - 2);
        }

        fis.close();

        return d;
    }
}
