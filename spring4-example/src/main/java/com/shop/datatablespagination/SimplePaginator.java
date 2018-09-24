package com.shop.datatablespagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.datatablespagination.data.TableDataException;
import com.shop.datatablespagination.data.TableDataService;
import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.datatablespagination.model.TablePage;

@Service
public class SimplePaginator implements TablePaginator {

	private final Logger LOGGER = LoggerFactory.getLogger(SimplePaginator.class);
	
    private TableDataService dataService;
    
    @Autowired
	public SimplePaginator(TableDataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public TablePage getPage(PaginationCriteria paginationCriteria) {
        TablePage page = new TablePage();
        try {
            page = generatePage(paginationCriteria);
        } catch (TableDataException tde) {
        	LOGGER.error("Error generating table page.", tde);
        }
        page.setError("Error generating table page.");
        return page;
    }

    protected TablePage generatePage(PaginationCriteria paginationCriteria) throws TableDataException {
        TablePage page = new TablePage();

        page.setDraw(paginationCriteria.getDraw());
        LOGGER.debug("Draw set...");

        page.setRecordsTotal(dataService.countTotalEntries());
        LOGGER.debug("RecordsTotal set...");

        page.setRecordsFiltered(dataService.countFilteredEntries(paginationCriteria));
        LOGGER.debug("RecordsFiltered set...");

        page.setData(dataService.getPageEntries(paginationCriteria));
        LOGGER.debug("Data set...");

        return page;
    }
}
