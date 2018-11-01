package com.finvendor.serverwebapi.resources.markets.dao;

import com.finvendor.model.Indice;
import com.finvendor.model.IndiceDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository
public class NiftyIndicesFilePersist extends AbstractNiftyFilePersist<Indice> {
    private static final Logger logger = LoggerFactory.getLogger(NiftyIndicesFilePersist.class.getName());

    @Override
    @Transactional
    public Long persist(String fromFilePath) throws RuntimeException {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";
        int idCounter = 1;
        long totalBsePriceInserted = 0L;
        try {
            br = new BufferedReader(new FileReader(fromFilePath));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] niftyColumns = line.split(cvsSplitBy);
                String indexId = "";
                String indexName = niftyColumns[0];
                String indexDate = niftyColumns[1];

                String openIndex = niftyColumns[2];
                String highIndex = niftyColumns[3];
                String lowIndex = niftyColumns[4];
                String closingIndex = niftyColumns[5];
                String pointChange = niftyColumns[6];

                String percentChange = niftyColumns[7];
                String volume = niftyColumns[8];
                String turnoverInCrore = niftyColumns[9];
                String pe = niftyColumns[10];
                String pb = niftyColumns[11];
                String divYield = niftyColumns[12];
                String type = "";
                String family = "NSE";
                Indice indice;
                indice = findById(idCounter);
                if (indice == null) {
                    indice = new Indice();
                    indice.setIndexId(indexId);
                    indice.setName(indexName);
                    indice.setType(type);
                    indice.setFamily(family);

                    IndiceDetails indiceDetails = new IndiceDetails();
                    indiceDetails.setDate(indexDate);
                    indiceDetails.setOpen(openIndex);
                    indiceDetails.setHigh(highIndex);
                    indiceDetails.setLow(lowIndex);
                    indiceDetails.setClosing(closingIndex);
                    indiceDetails.setPointChange(pointChange);
                    indiceDetails.setPercentChange(percentChange);
                    indiceDetails.setVolume(volume);
                    indiceDetails.setTurnoverInCrore(turnoverInCrore);
                    indiceDetails.setPe(pe);
                    indiceDetails.setPb(pb);
                    indiceDetails.setDivYield(divYield);

                    indice.setIndiceDetails(indiceDetails);
                } else {
                    IndiceDetails indiceDetails = indice.getIndiceDetails();
                    indiceDetails.setDate(indexDate);
                    indiceDetails.setOpen(openIndex);
                    indiceDetails.setHigh(highIndex);
                    indiceDetails.setLow(lowIndex);
                    indiceDetails.setClosing(closingIndex);
                    indiceDetails.setPointChange(pointChange);
                    indiceDetails.setPercentChange(percentChange);
                    indiceDetails.setVolume(volume);
                    indiceDetails.setTurnoverInCrore(turnoverInCrore);
                    indiceDetails.setPe(pe);
                    indiceDetails.setPb(pb);
                    indiceDetails.setDivYield(divYield);
                    indice.setIndiceDetails(indiceDetails);
                }
                save(indice);
                idCounter++;
                totalBsePriceInserted++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.warn("Unable to close buffered reader as it is null");
                }
            }
        }
        return totalBsePriceInserted;
    }
}
