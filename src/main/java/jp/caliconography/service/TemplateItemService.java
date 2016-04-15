package jp.caliconography.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jp.caliconography.domain.TemplateItem;
import jp.caliconography.respository.TemplateItemRepository;

@Service
public class TemplateItemService {
	@Autowired
	TemplateItemRepository TemplateItemRepository;

	public List<TemplateItem> findByTemplateIdOrderByCreatedAt(String templateId) {
		return TemplateItemRepository.findByTemplateIdOrderByCreatedAt(templateId);
	}

	public TemplateItem findOne(String id) {
		return TemplateItemRepository.findOne(id);
	}

	public TemplateItem create(TemplateItem TemplateItem) {
		return TemplateItemRepository.save(TemplateItem);
	}

	public TemplateItem update(TemplateItem TemplateItem) {
		return TemplateItemRepository.save(TemplateItem);
	}

	public void delete(String id) {
		TemplateItemRepository.delete(id);
	}
}