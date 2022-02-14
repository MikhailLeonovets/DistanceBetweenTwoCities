package com.itechart.demo.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum RouteValidationFeature implements Feature {

	@EnabledByDefault
	@Label("validation_feature")
	VALIDATION_FEATURE;

	public boolean isActive() {
		return FeatureContext.getFeatureManager().isActive(this);
	}
}
