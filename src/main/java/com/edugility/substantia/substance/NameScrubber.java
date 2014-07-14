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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Collection;
import java.util.Iterator;

import com.edugility.nomen.Name;

final class NameScrubber implements PropertyChangeListener {

  private NamedSubstance<?, ?> namedSubstance;

  NameScrubber(final NamedSubstance<?, ?> namedSubstance) {
    super();
    if (namedSubstance == null) {
      throw new IllegalArgumentException("namedSubstance", new NullPointerException("namedSubstance"));
    }
    this.namedSubstance = namedSubstance;
  }

  @Override
  public final void propertyChange(final PropertyChangeEvent event) {
    if (event != null && "named".equals(event.getPropertyName()) && this.namedSubstance != null && this.namedSubstance == event.getOldValue()) {
      final Object source = event.getSource();
      if (source instanceof Name  && this.namedSubstance != event.getNewValue()) {
        final Name name = (Name)source;
        final Collection<Name> names = this.namedSubstance.getNames();
        if (names != null && !names.isEmpty()) {
          final Iterator<Name> nameIterator = names.iterator();
          if (nameIterator != null) {
            while (nameIterator.hasNext()) {
              final Name n = nameIterator.next();
              if (n == name) {
                n.removePropertyChangeListener("named", this);
                nameIterator.remove();
              }
            }
            this.namedSubstance = null;
          }
        }
      }
    }
  }

}
