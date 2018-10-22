package com.finvendor.server.markets.dao.impl;

import com.finvendor.model.Indice;
import com.finvendor.model.IndiceDetails;
import com.finvendor.server.markets.dao.AbstractNiftyFilePersist;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Repository
public class NiftyIndicesFilePersist extends AbstractNiftyFilePersist<Indice> {

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
                String indexId="";
//                if (String.valueOf(idCounter).length() == 1) {
//                    id = "000" + idCounter;
//                } else if (String.valueOf(idCounter).length() == 2) {
//                    id = "00" + idCounter;
//                } else if (String.valueOf(idCounter).length() == 3) {
//                    id = "0" + idCounter;
//                } else {
//                    id = "" + idCounter;
//                }
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

                Indice index = new Indice();
                index.setIndexId(indexId);
                index.setName(indexName);
                index.setType(type);
                index.setFamily(family);

                IndiceDetails indiceDetails =new IndiceDetails();
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

                index.setIndiceDetails(indiceDetails);
                save(index);
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

                }
            }
        }
        return totalBsePriceInserted;
    }
}
