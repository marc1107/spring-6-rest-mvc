package marc.springframework.spring6restmvc.services;

import com.opencsv.bean.CsvToBeanBuilder;
import marc.springframework.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BeerCsvServiceImpl implements BeerCsvService
{
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile)
    {
        List<BeerCSVRecord> beerCSVRecords = null;
        try
        {
            beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build()
                    .parse();
            return beerCSVRecords;
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
}
