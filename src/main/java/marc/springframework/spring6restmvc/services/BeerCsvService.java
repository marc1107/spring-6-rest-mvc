package marc.springframework.spring6restmvc.services;

import marc.springframework.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService
{
    List<BeerCSVRecord> convertCSV(File file);
}
