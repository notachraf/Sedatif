package pmn.sedatif.worker;

import pmn.sedatif.SedatifApplication;
import pmn.sedatif.models.Document;
import pmn.sedatif.models.Tache;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class worker {

    public static List<CSVObject> getCSVObjectsTravaux() throws IOException, ParseException {

        List<CSVObject> csvObjects = new ArrayList<>();

        for (File dir: listFilesInResourceFolder("static/TPRE716/SEDATIF", true)) {

            List<File> xmlFiles = listFilesInResourceFolder("static/TPRE716/SEDATIF/" + dir.getName(), false);

            File latestModifiedFile = getLatestModifiedFile(xmlFiles);

            Document init = worker.mappingXMLToObject(dir.getName() + "/init.xml", Document.class);
            Document latest = worker.mappingXMLToObject(dir.getName() + "/" + latestModifiedFile.getName(), Document.class);

            String name = latest.getChantiers().get(0).getEspaces().get(0).getName();
            if (name == null) {
                name = init.getChantiers().get(0).getEspaces().get(0).getName();
            }

            String adresse = latest.getChantiers().get(0).getEspaces().get(0).getAdresse();
            if (adresse == null) {
                adresse = init.getChantiers().get(0).getEspaces().get(0).getAdresse();
            }

            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            String latest_df = "00-00-0000";
            Date latest_df_date = dateFormat.parse(latest_df);

            for (Tache tache: latest.getChantiers().get(0).getTaches()) {
                try {
                    String df = tache.getDf();
                    Date df_date = dateFormat.parse(df);

                    if (df_date.after(latest_df_date)) {
                        latest_df_date = df_date;
                    }
                } catch (ParseException e) {
                }
            }
            latest_df = new SimpleDateFormat("dd-MM-yyyy").format(latest_df_date);
            csvObjects.add(new CSVObject(name, adresse, latest_df));
        }
        return csvObjects;
    }

    public static List<CSVObject> getCSVObjectsOuvert() {
        List<CSVObject> csvObjects = new ArrayList<>();


        for (File dir: listFilesInResourceFolder("static/TPRE716/SEDATIF", true)) {

            List<File> xmlFiles = listFilesInResourceFolder("static/TPRE716/SEDATIF/" + dir.getName(), false);

            File latestModifiedFile = getLatestModifiedFile(xmlFiles);

            Document init = worker.mappingXMLToObject(dir.getName() + "/init.xml", Document.class);
            Document latest = worker.mappingXMLToObject(dir.getName() + "/" + latestModifiedFile.getName(), Document.class);

            String name = latest.getChantiers().get(0).getEspaces().get(0).getName();
            if (name == null) {
                name = init.getChantiers().get(0).getEspaces().get(0).getName();
            }

            String adresse = latest.getChantiers().get(0).getEspaces().get(0).getAdresse();
            if (adresse == null) {
                adresse = init.getChantiers().get(0).getEspaces().get(0).getAdresse();
            }

            Boolean ouvert = null;
            for (Tache tache: latest.getChantiers().get(0).getTaches()) {
                ouvert = true;
                if (tache.getEtat().equals("En cours")){
                    ouvert = false;
                    break;
                }
            }
            if (ouvert == true) {
                csvObjects.add(new CSVObject(name, adresse));
            }
        }
        return csvObjects;
    }


        public static <T> T mappingXMLToObject(String xmlFilePath, Class<T> clazz) {
        ClassLoader classLoader = worker.class.getClassLoader();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Load the XML file and unmarshal it
            InputStream xmlStream = classLoader.getResourceAsStream("static/TPRE716/SEDATIF/" + xmlFilePath);
            return clazz.cast(unmarshaller.unmarshal(xmlStream));
        } catch (JAXBException e) {
            e.printStackTrace();
            // Handle JAXB exception
            return null;
        }
    }

    public static List<File> listFilesInResourceFolder(String folderName, boolean directory) {
        List<File> fileList = new ArrayList<>();
        ClassLoader classLoader = SedatifApplication.class.getClassLoader();
        if (classLoader == null) {
            return fileList; // Return an empty list if the class loader is not available.
        }
        // Get the URL for the folder in the resources directory.
        java.net.URL resource = classLoader.getResource(folderName);
        if (resource == null) {
            return fileList; // Return an empty list if the folder is not found.
        }

        // Convert the URL to a file path (works for both JAR and file system resources).
        String resourcePath = resource.getPath();
        if (resourcePath.startsWith("file:")) {
            resourcePath = resourcePath.substring("file:".length());
        }

        File folder = new File(resourcePath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (directory && file.isDirectory()) {
                        fileList.add(file);
                    }
                    if (!directory && file.isFile()) {
                        fileList.add(file);
                    }
                }
            }
        }
        return fileList;
    }

    private static File getLatestModifiedFile(List<File> xmlFiles) {
        if (xmlFiles == null || xmlFiles.isEmpty()) {
            return null;
        }

        if (xmlFiles.size() == 1 && xmlFiles.get(0).getName().equals("init.xml")) {
            return xmlFiles.get(0);
        }

        File latestModifFile = null;

        for (File xmlFile : xmlFiles) {
            String fileName = xmlFile.getName();
            if (fileName.startsWith("modif-") && fileName.endsWith(".xml")) {
                Date fileDate = parseDateFromFileName(fileName);
                if (fileDate != null) {
                    if (latestModifFile == null || fileDate.after(parseDateFromFileName(latestModifFile.getName()))) {
                        latestModifFile = xmlFile;
                    }
                }
            }
        }
        return latestModifFile;
    }

    private static Date parseDateFromFileName(String fileName) {
        try {
            String dateStr = fileName.substring("modif-".length(), fileName.length() - ".xml".length());
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
