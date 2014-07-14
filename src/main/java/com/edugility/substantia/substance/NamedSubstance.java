/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright (c) 2014 Edugility LLC.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * The original copy of this license is available at
 * http://www.opensource.org/license/mit-license.html.
 */
package com.edugility.substantia.substance;

import java.beans.PropertyChangeListener;

import java.io.Serializable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.edugility.nomen.Name;
import com.edugility.nomen.NameSupport;
import com.edugility.nomen.NameValue;
import com.edugility.nomen.MutableNamed;
import com.edugility.nomen.Valued;
import com.edugility.nomen.NameType;

/**
 * A {@linkplain AbstractNamed named} substrate with an {@linkplain Id
 * affiliated <code>Id</code>} to which properties and attributes may
 * be attached.
 *
 * <p>A {@link Substance} serves to "take up space" in a coordinate
 * system.  It is an {@link AbstractNamed} implementation, so may
 * carry {@link Name}s of various kinds.</p>
 */
public abstract class NamedSubstance<I extends Serializable, V extends Comparable<V> & Serializable> extends Substance<I, V> implements MutableNamed {

  
  /*
   * Static fields.
   */


  private static final long serialVersionUID = 1L;

  public static final NameType DISPLAY_NAME_NAME_TYPE = new NameType("displayName");

  public static final NameType SORT_NAME_NAME_TYPE = new NameType("sortName");

  /*
   * Instance fields.
   */


  /**
   * A {@link Map} of the {@link Name}s this {@link NamedSubstance}
   * has, indexed by {@link NameType}.
   *
   * <p>This field may be {@code null}.</p>
   */
  private Map<NameType, Name> names;

  private NameSupport nameSupport;


  /*
   * Constructors.
   */


  protected NamedSubstance() {
    super();
    this.names = new HashMap<NameType, Name>();
    this.nameSupport = new NameSupport(this);
  }


  /*
   * Instance methods.
   */


  @Override
  public Name getName(final NameType nameType) {
    Name name = null;
    if (nameType != null && this.names != null && !this.names.isEmpty()) {
      name = this.names.get(nameType);
      assert name == null ? true : name.getNamed() == this;
    }
    return name;
  }

  /**
   * Stores the supplied {@link Name} under the supplied {@link
   * NameType} in such a way that it can be retrieved later by the
   * #getName(NameType)} method when that method is supplied with a
   * {@link NameType} {@linkplain NameType#equals(Object) equal to}
   * the supplied {@link NameType}.
   *
   * <h3>Implementation Notes</h3>
   *
   * <p>To properly orphan {@link Name}s that are no longer indexed
   * under a given {@link NameType} in this {@link NamedSubstance},
   * {@linkplain #getNames() all the <code>Name</code>s affiliated
   * with this <code>NamedSubstance</code> implementation} must be
   * scanned, so this operation has performance that decreases as the
   * total number of {@link Name}s stored by this {@link
   * NamedSubstance} implementation increases.</p>
   *
   * @param nameType a {@link NameType} under which a new {@link Name}
   * will be stored; must not be {@code null}
   *
   * @param name a {@link Name} to store; must not be {@code null};
   * its {@link Name#setNamed(Named)} method will be called with
   * {@code this} as its argument.
   *
   * @return the prior {@link Name} stored under the supplied {@link
   * NameType}, or {@code null} if there was no such {@link Name}.
   * The returned {@link Name}, if non-{@code null}, will return
   * {@code null} from its {@link Name#getNamed()} method only if it
   * is now truly orphaned, that is only if it is not indexed in this
   * {@link NamedSubstance} under any other {@link NameType}.
   *
   * @exception IllegalArgumentException if {@code nameType} or {@code
   * name} is {@code null}, or if {@code nameType} is unsuitable, or
   * if {@code name} is unsuitable
   */
  @Override
  public Name putName(final NameType nameType, final Name name) {
    return this.nameSupport.putName(this.names, nameType, name);
  }

  /**
   * Removes the single {@link Name} stored under a {@link NameType}
   * that is {@linkplain AbstractValued#equals(Object) equal} or
   * identical to the supplied {@link NameType} and returns it.
   *
   * <p>This method may return {@code null} if there is no {@link
   * Name} indexed under a {@link NameType} {@linkplain
   * AbstractValued#equals(Object) equal} or identical to the supplied
   * {@link NameType}.</p>
   *
   * @param nameType the {@link NameType} to use to find the {@link
   * Name} in question; must not be {@code null}
   *
   * @return the {@link Name} that was removed, or {@code null}.  The
   * {@link Name} that is returned, if non-{@code null}, will return
   * {@code null} from its {@link Name#getNamed()} method.
   *
   * @exception IllegalArgumentException if {@code nameType} is {@code
   * null}
   */
  @Override
  public Name removeName(final NameType nameType) {
    return this.nameSupport.removeName(this.names, nameType);
  }

  /**
   * Returns a non-{@code null} {@link Set} of {@link NameType}
   * instances, each element of which can be used as an argument to
   * the {@link #getName(NameType)} method.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @return a non-{@code null} {@link Set} of {@link NameType}s
   */
  public Set<NameType> getNameTypes() {
    return this.nameSupport.getNameTypes(this.names);
  }

  /**
   * Returns a non-{@code null} {@link Collection} of {@link Name}s
   * that this {@link NamedSubstance} is known by.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @return a non-{@code null} {@link Collection} of {@link Name}s
   */
  public Collection<Name> getNames() {
    return this.nameSupport.getNames(this.names);
  }

  public abstract I getId();

  public abstract V getVersion();

  public String getDisplayName() {
    final String returnValue;
    final Valued displayName = this.getName(DISPLAY_NAME_NAME_TYPE);
    if (displayName == null) {
      returnValue = null;
    } else {
      returnValue = displayName.getValue();
    }
    return returnValue;
  }

  public String getSortName() {
    final String returnValue;
    final Valued sortName = this.getName(SORT_NAME_NAME_TYPE);
    if (sortName == null) {
      returnValue = this.getDisplayName();
    } else {
      returnValue = sortName.getValue();
    }
    return returnValue;
  }

}
