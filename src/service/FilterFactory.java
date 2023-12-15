package service;

import enums.FilterType;

public class FilterFactory {

    public FilterStrategy getFilterBy(FilterType filter) {
        if (FilterType.ASCENDING_DEADLINE.equals(filter)) {
            return new FilterByDeadlineStrategy();
        } else if (FilterType.IN_PROGRESS_TASK_STATUS.equals(filter)) {
            return new FilterByTaskStatus();
        } else {
            throw new IllegalArgumentException("Unsupported filter strategy: " + filter);
        }
    }
}
