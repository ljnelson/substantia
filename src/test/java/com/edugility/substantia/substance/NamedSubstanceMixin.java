package com.edugility.substantia.substance;

import java.util.Map;

import com.edugility.nomen.Name;
import com.edugility.nomen.NameType;
import com.edugility.nomen.Named;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public abstract class NamedSubstanceMixin {

  @JsonManagedReference
  private Map<NameType, Name> names;

  @JsonBackReference
  public abstract Named getNamed();

}
