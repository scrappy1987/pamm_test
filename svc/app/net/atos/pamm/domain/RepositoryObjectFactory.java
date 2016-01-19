package net.atos.pamm.domain;

import net.atos.pamm.infrastructure.util.JSONHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RepositoryObjectFactory {
    private final JSONHelper jsonHelper;

    @Inject
    public RepositoryObjectFactory(JSONHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public <T> T createEntity(Object businessObject, Class<T> entityClass) {
        final String jsonString = jsonHelper.getJSONForObject(businessObject);
        return jsonHelper.getObjectForJSON(jsonString, entityClass);
    }

    public <S> S createBusinessObject(Object entityObject, Class<S> boClass) {
        final String jsonString = jsonHelper.getJSONForObject(entityObject);
        return jsonHelper.getObjectForJSON(jsonString, boClass);
    }
}
