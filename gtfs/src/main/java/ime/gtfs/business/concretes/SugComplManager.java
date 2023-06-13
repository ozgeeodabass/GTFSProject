package ime.gtfs.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.gtfs.business.abstracts.SugComplService;
import ime.gtfs.core.utilities.results.DataResult;
import ime.gtfs.core.utilities.results.Result;
import ime.gtfs.core.utilities.results.SuccessDataResult;
import ime.gtfs.core.utilities.results.SuccessResult;
import ime.gtfs.dataAccess.abstracts.SugComplRepository;
import ime.gtfs.entities.SugCompl;

@Service
public class SugComplManager implements SugComplService {
	
	private SugComplRepository sugComplRepository;
	
	
	@Autowired
	public SugComplManager(SugComplRepository sugComplRepository) {
		super();
		this.sugComplRepository = sugComplRepository;
	}

	@Override
	public DataResult<List<SugCompl>> getAll() {
	
		return new SuccessDataResult<List<SugCompl>>(this.sugComplRepository.findAll());
	}

	@Override
	public Result add(int route,String email, String phone, String text) {
		SugCompl sugcomplobject = new SugCompl();
		sugcomplobject.setEmail(email);
		sugcomplobject.setPhone(phone);
		sugcomplobject.setRoute(route);
		sugcomplobject.setText(text);
	
		
		this.sugComplRepository.save(sugcomplobject);
		
		return new SuccessResult("Eklendi");
		
	}

	@Override
	public DataResult<List<SugCompl>> findAllByRoute(int route) {
		
		return new SuccessDataResult<List<SugCompl>>(this.sugComplRepository.findAllByRoute(route));
	}

}
