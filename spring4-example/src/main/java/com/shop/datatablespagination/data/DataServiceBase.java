package com.shop.datatablespagination.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.datatablespagination.model.PaginationCriteria;

public abstract class DataServiceBase<T> implements TableDataService {

	private final Logger LOGGER = LoggerFactory.getLogger(DataServiceBase.class);
	
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Map<String, String>> getPageEntries(PaginationCriteria paginationCriteria) throws TableDataException {
        List<T> data = getData(paginationCriteria);
        LOGGER.debug("Table data retrieved...");

        List<Map<String, String>> records = new ArrayList<>(data.size());
        try {
            data.forEach(i -> {
            	Map<String, Object> m = objectMapper.convertValue(i, Map.class);
            	Map<String, String> record = new LinkedHashMap<>();;
                for(Map.Entry<String, Object> entry : m.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue() == null ? null : entry.getValue().toString();
                    record.put(key, value);
                }
                records.add(record);
            });
            LOGGER.debug("Data map generated...");
        } catch (Exception e) {
        	LOGGER.error("Error fetching page entries.", e);
            throw new TableDataException("", e);
        }
        return records;
    }

    protected abstract List<T> getData(PaginationCriteria paginationCriteria) throws TableDataException;

}
