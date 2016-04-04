package jp.caliconography.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.caliconography.domain.Template;
import jp.caliconography.respository.TemplateRepository;

@Service
public class TemplateService {
	@Autowired
	TemplateRepository TemplateRepository;

	public List<Template> findByUsernameOrderByCreatedAt(String username) {
		return TemplateRepository.findByUsernameOrderByCreatedAt(username);
	}

	public Template findOne(String id) {
		return TemplateRepository.findOne(id);
	}

	public Template create(Template Template) {
		return TemplateRepository.save(Template);
	}

	public Template update(Template Template) {
		return TemplateRepository.save(Template);
	}

	public void delete(String id) {
		TemplateRepository.delete(id);
	}
}