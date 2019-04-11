package io.crnk.core.engine.internal.repository;

import io.crnk.core.engine.information.repository.RelationshipRepositoryInformation;
import io.crnk.core.engine.information.repository.ResourceRepositoryInformation;
import io.crnk.core.engine.information.resource.ResourceField;
import io.crnk.core.module.ModuleRegistry;
import io.crnk.core.repository.RelationshipRepositoryV2;
import io.crnk.core.repository.ResourceRepositoryV2;
import io.crnk.legacy.repository.LegacyRelationshipRepository;
import io.crnk.legacy.repository.LegacyResourceRepository;

import java.util.Objects;

public class DefaultRepositoryAdapterFactory implements RepositoryAdapterFactory {

	private final ModuleRegistry moduleRegistry;

	public DefaultRepositoryAdapterFactory(ModuleRegistry moduleRegistry) {
		this.moduleRegistry = moduleRegistry;
	}

	@Override
	public boolean accepts(Object repository) {
		Objects.requireNonNull(repository);
		return repository instanceof LegacyResourceRepository || repository instanceof ResourceRepositoryV2
				|| repository instanceof LegacyRelationshipRepository || repository instanceof RelationshipRepositoryV2;
	}

	@Override
	public ResourceRepositoryAdapter createResourceRepositoryAdapter(ResourceRepositoryInformation information, Object repository) {
		return new ResourceRepositoryAdapterImpl(information, moduleRegistry, repository);
	}

	@Override
	public RelationshipRepositoryAdapter createRelationshipRepositoryAdapter(ResourceField field, RelationshipRepositoryInformation information, Object repository) {
		return new RelationshipRepositoryAdapterImpl(field, moduleRegistry, repository);
	}

	@Override
	public ResourceRepositoryAdapter decorate(ResourceRepositoryAdapter adapter) {
		return adapter;
	}

	@Override
	public RelationshipRepositoryAdapter decorate(RelationshipRepositoryAdapter adapter) {
		return adapter;
	}
}
