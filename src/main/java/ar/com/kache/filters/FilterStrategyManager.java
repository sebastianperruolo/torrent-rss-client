package ar.com.kache.filters;

import java.util.HashMap;
import java.util.Map;

public class FilterStrategyManager {
	
	private static FilterStrategyManager instance;
	
	public static FilterStrategyManager getInstance() {
		if (instance == null) {
			instance = new FilterStrategyManager();
		}
		return instance;
	}
	
	private Map<String, IFilterStrategy> filters;
	
	private IFilterStrategy defaultFilter;
	
	private FilterStrategyManager() {
		filters = new HashMap<String, IFilterStrategy>();
		filters.put(BlackListFilterStrategy.CODE, new BlackListFilterStrategy());
		filters.put(WhiteListFilterStrategy.CODE, new WhiteListFilterStrategy());
		filters.put(RegExFilterStrategy.CODE, new RegExFilterStrategy());
		defaultFilter = new NullFilterStrategy();
	}

	public IFilterStrategy getFilterFor(String filterStrategy) {
		if (filterStrategy == null) {
			return defaultFilter;
		}
		IFilterStrategy result = filters.get(filterStrategy);
		if (result != null) {
			return result;
		}
		return defaultFilter;
	}
}
