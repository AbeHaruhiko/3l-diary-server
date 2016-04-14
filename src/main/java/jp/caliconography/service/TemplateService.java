package jp.caliconography.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.caliconography.domain.Template;
import jp.caliconography.domain.TemplateItem;
import jp.caliconography.respository.TemplateItemRepository;
import jp.caliconography.respository.TemplateRepository;

@Service
public class TemplateService {
	@Autowired
	TemplateRepository TemplateRepository;
	
	@Autowired
	TemplateItemRepository TemplateItemRepository;
	
	@PersistenceContext
	private EntityManager em;
	

	public List<Template> findByUsernameOrderByCreatedAt(String username) {
		return TemplateRepository.findByUsernameOrderByCreatedAt(username);
	}

	public Template findOne(String id) {
		return TemplateRepository.findOne(id);
	}

	public Template create(Template Template) {

//		Template savedTemplate = TemplateRepository.save(Template);
//		List<TemplateItem> templateItems = TemplateItemRepository.save(Template.getTemplateItems());
		
		return TemplateRepository.save(Template);
	}

	public Template update(Template Template) {
		return TemplateRepository.save(Template);
	}

	public void delete(String id) {
		TemplateRepository.delete(id);
	}
}