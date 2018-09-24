package com.shop.datatablespagination;

import com.shop.datatablespagination.model.PaginationCriteria;
import com.shop.datatablespagination.model.TablePage;

/**
 * The main component, used to generate a {@link TablePage}} according to
 * {@link PaginationCriteria}.
 *
 * @author David Castelletti
 *
 */
public interface TablePaginator {
	TablePage getPage(PaginationCriteria paginationCriteria);
}
