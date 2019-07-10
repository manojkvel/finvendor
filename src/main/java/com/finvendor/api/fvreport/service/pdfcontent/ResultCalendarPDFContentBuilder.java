package com.finvendor.api.fvreport.service.pdfcontent;

import com.finvendor.api.fvreport.dao.FvReportDao;
import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendar;
import com.finvendor.api.fvreport.dto.resultCalendar.ResultCalendarPDFContent;
import com.finvendor.common.infra.pdf.IPDFContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResultCalendarPDFContentBuilder implements IPDFContentBuilder<String, ResultCalendarPDFContent> {

    @Autowired
    protected FvReportDao reportDao;

    @Override
    public ResultCalendarPDFContent buildContent(String userName) throws Exception {
        List<ResultCalendar> resultCalendar = reportDao.findResultCalendar();
        return new ResultCalendarPDFContent(userName, resultCalendar);
    }
}
