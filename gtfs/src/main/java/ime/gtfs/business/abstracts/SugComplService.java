package ime.gtfs.business.abstracts;

import java.util.List;

import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.entities.SugCompl;

public interface SugComplService {
	

	DataResult<List<SugCompl>> getAll();
	Result add(int route,String email, String phone, String text);
	DataResult<List<SugCompl>> findAllByRoute(int route);

}
