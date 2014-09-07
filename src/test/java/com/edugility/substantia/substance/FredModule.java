package com.edugility.substantia.substance;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.Module.SetupContext;

import com.fasterxml.jackson.core.Version;

public class FredModule extends SimpleModule {

  private static final long serialVersionUID = 1L;

  public FredModule() {
    super("FredModule", new Version(1, 0, 0, "SNAPSHOT", "com.edugility", "substantia"));
  }

  @Override
  public void setupModule(final SetupContext context) {
    if (context != null) {
      context.setMixInAnnotations(com.edugility.nomen.Name.class, NamedSubstanceMixin.class);
      context.setMixInAnnotations(NamedSubstance.class, NamedSubstanceMixin.class);
    }
  }
}
