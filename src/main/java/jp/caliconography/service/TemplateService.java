package jp.caliconography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jp.caliconography.domain.Template;
import jp.caliconography.respository.TemplateRepository;

@Service
public class TemplateService {
	@Autowired
	TemplateRepository TemplateRepository;

	public Page<Template> findAll(Pageable pageable) {
		return TemplateRepository.findAllOrderByName(pageable);
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