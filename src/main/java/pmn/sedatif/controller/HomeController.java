package pmn.sedatif.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import pmn.sedatif.worker.CSVObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static pmn.sedatif.worker.CSVObject.convertToCsvString;
import static pmn.sedatif.worker.worker.getCSVObjectsOuvert;
import static pmn.sedatif.worker.worker.getCSVObjectsTravaux;


@RestController
@RequestMapping("/espace")
public class HomeController  {

    @GetMapping("/travaux")
    public ResponseEntity<byte[]> getTravaux() throws IOException, ParseException {

        List<CSVObject> csvObjects = getCSVObjectsTravaux();
        String csv = convertToCsvString(csvObjects, true);

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.add("Content-Disposition", "attachment; filename=data.csv");

        // Convert the CSV string to bytes and return it
        return ResponseEntity.ok()
                .headers(headers)
                .body(csv.getBytes());

    }

    @GetMapping("/ouverts")
    public ResponseEntity<byte[]> getEspaceOuvert() throws IOException, ParseException {

        List<CSVObject> csvObjects = getCSVObjectsOuvert();
        String csv = convertToCsvString(csvObjects, false);

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.add("Content-Disposition", "attachment; filename=data.csv");

        // Convert the CSV string to bytes and return it
        return ResponseEntity.ok()
                .headers(headers)
                .body(csv.getBytes());

    }

}
